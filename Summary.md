**Containerizing a JAVA Application**



**Workflow** 

![image-20210323222415716](C:\Users\rachaube\AppData\Roaming\Typora\typora-user-images\image-20210323222415716.png)

What is the JAVA code

Application: Java Application
Frame Work: Spring Boot

We are going to use a Simple JAVA application built using spring bsudo apt install mavenoot.

**Step #1: Build and test Application using Maven**

Package the Application on a linux box:

$ sudo apt update
$ sudo apt install maven
$ mvn -version
$ mvn sprin-boot:run

Take a duplicate session :
curl http://localhost:8080/hello
Hello

Or Open the Port 8080 on NSG and access the Application form browser:
![image-20210323204016731](C:\Users\rachaube\AppData\Roaming\Typora\typora-user-images\image-20210323204016731.png)

![image-20210323204025075](C:\Users\rachaube\AppData\Roaming\Typora\typora-user-images\image-20210323204025075.png)



**Step#2: Package our Application using Docker**

As we discussed during the session that creating a Docker image is an important step in **containerizing** any Application. So, here in this step we are going to see how a JAVA application can be packaged and run as a Container Image.



**Basic Docker Workflow:**

![img](https://docs.docker.com/engine/images/architecture.svg)

[Source of the Image: https://docs.docker.com/get-started/overview/]



2.1: Create a Dockerfile

 A `Dockerfile` is a text document that contains all the commands a user could call on the command line to assemble an image. 

Using `docker build` users can create an automated build that executes several command-line instructions in succession.

**Sample Dockerfile**:

```
FROM ubuntu:18.04
COPY . /app
RUN make /app
CMD python /app/app.py
```

Each instruction creates one layer:

- `FROM` creates a layer from the `ubuntu:18.04` Docker image.
- `COPY` adds files from your Docker client’s current directory.
- `RUN` builds your application with `make`.
- `CMD` specifies what command to run within the container.



**Multi-Stage Build**

Multiple FROM statements, each FROM statement can use a different base and each of them begins a new stage of the build. You can selectively copy the artifacts from one stage to another, by this we can keep the size of the Dockerfile lean.



![](C:\Users\rachaube\AppData\Roaming\Typora\typora-user-images\image-20210323222519786.png)



Here in this scenario, I'm going to use the **maven**  in development phase and **openjdk:jre** in production phase (the **openjdk** Docker image is created and maintained by the community).

You can also consider to use **amazoncorretto**,  this is maintained by Amazon.

**Build Context**

The Docker image is build using docker image build command. You've to provide the path or the URL of the Dockerfile.

Each image is uniquely identified by the **name** and the **tag** (if no tag is  provided the default Latest tag is provided).

```
docker image build -t name:tag .
```

**Run the Container**

From the image, we can run containers.

```
docker container run <image_name>

-d : run the container in background
-it : interactive

docker container run -p <host_port>:<container_port>
```



**Dockerizing Java Apps using Jib**

*Jib* is an open-source Java tool maintained by Google for building Docker images of Java applications.It simplifies your life.

Google publishes Jib as both a Maven and a Gradle plugin. This is nice because it means that Jib will catch any changes we make to our application each time we build.

Docker daemon is not needed to build the image, I.E. No need to write the Dockefile :-)



**References**:

https://docs.docker.com/
https://docs.docker.com/engine/reference/builder/
https://docs.docker.com/develop/develop-images/dockerfile_best-practices/