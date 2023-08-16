
## Welcome to repository cqrs-user-service !


### Pre-requirements for installation
1. Have an **IDE** installed of preference
2. Have **Git** installed https://git-scm.com/downloads
3. Have **Docker** installed https://www.docker.com/products/docker-desktop/
4. Have **Gradle** installed https://gradle.org/install/
5. Have cloned **the repository**: https://github.com/Ramir0/cqrs-user-service

### Steps

- Open Docker desktop an click on containers
- Make sure docker is running
- From command line **(cmd)** go to the project folder (the folder where the repository is cloned)
- Once in the main project folder, open the **cqrs-user-query-service** folder and run the following command:
	**docker-compose up -d**	
- Go back to the main project folder, open the **cqrs-user-command-service** folder, eject the following command:
**docker-compose up -d**
- When running the two projects (command, query) the port 8081 and 8082 appears without error in the editor console we can say that everything is fine.