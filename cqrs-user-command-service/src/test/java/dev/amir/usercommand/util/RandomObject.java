package dev.amir.usercommand.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.RecordComponent;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.ObjectCreationException;
import org.jeasy.random.ObjenesisObjectFactory;
import org.jeasy.random.api.RandomizerContext;

public final class RandomObject {
    private static final EasyRandom RANDOM_OBJECT = new EasyRandom(
            new EasyRandomParameters().objectFactory(new RecordFactory())
    );

    public static <T> T nextObject(Class<T> type) {
        return RANDOM_OBJECT.nextObject(type);
    }

    private RandomObject() {
        // Prevent instantiation
    }

    private static class RecordFactory extends ObjenesisObjectFactory {

        private EasyRandom easyRandom;

        @Override
        public <T> T createInstance(Class<T> type, RandomizerContext context) {
            if (easyRandom == null) {
                easyRandom = new EasyRandom(context.getParameters());
            }

            if (type.isRecord()) {
                return createRandomRecord(type);
            } else {
                return super.createInstance(type, context);
            }
        }

        private <T> T createRandomRecord(Class<T> recordType) {
            RecordComponent[] recordComponents = recordType.getRecordComponents();
            Object[] randomValues = new Object[recordComponents.length];
            for (int i = 0; i < recordComponents.length; i++) {
                randomValues[i] = easyRandom.nextObject(recordComponents[i].getType());
            }
            try {
                return getCanonicalConstructor(recordType).newInstance(randomValues);
            } catch (Exception e) {
                throw new ObjectCreationException("Unable to create a random instance of recordType " + recordType, e);
            }
        }

        private <T> Constructor<T> getCanonicalConstructor(Class<T> recordType) {
            RecordComponent[] recordComponents = recordType.getRecordComponents();
            Class<?>[] componentTypes = new Class<?>[recordComponents.length];
            for (int i = 0; i < recordComponents.length; i++) {
                componentTypes[i] = recordComponents[i].getType();
            }
            try {
                return recordType.getDeclaredConstructor(componentTypes);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Invalid record definition", e);
            }
        }
    }
}
