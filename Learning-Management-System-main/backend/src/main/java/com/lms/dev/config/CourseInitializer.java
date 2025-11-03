package com.lms.dev.config;

import com.lms.dev.entity.Course;
import com.lms.dev.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CourseInitializer {

    private final CourseRepository courseRepository;

    @Bean
    public CommandLineRunner initializeCourses() {
        return args -> {
            try {
                long existingCount = courseRepository.count();
                log.info("Current course count: {}", existingCount);
                
                if (existingCount == 0) {
                    log.info("Initializing default courses...");

                // Python Basics
                Course pythonBasics = new Course();
                pythonBasics.setCourse_name("Python Basics");
                pythonBasics.setInstructor("Alice");
                pythonBasics.setPrice(50);
                pythonBasics.setDescription("Master the fundamentals of Python programming. Learn syntax, data types, control structures, and object-oriented programming.");
                pythonBasics.setP_link("https://img-c.udemycdn.com/course/750x422/394676_ce3d_5.jpg");
                pythonBasics.setY_link("https://www.youtube.com/watch?v=kqtD5dpn9C8");
                courseRepository.save(pythonBasics);

                // Web Development
                Course webDevelopment = new Course();
                webDevelopment.setCourse_name("Web Development");
                webDevelopment.setInstructor("Bob");
                webDevelopment.setPrice(75);
                webDevelopment.setDescription("Build modern, responsive websites using HTML5, CSS3, JavaScript, and popular frameworks. Learn full-stack development.");
                webDevelopment.setP_link("https://img-c.udemycdn.com/course/750x422/851712_fc61_6.jpg");
                webDevelopment.setY_link("https://youtu.be/zJSY8tbf_ys?si=5X_Ty76TrJfVUd7u");
                courseRepository.save(webDevelopment);

                // Data Structures
                Course dataStructures = new Course();
                dataStructures.setCourse_name("Data Structures");
                dataStructures.setInstructor("Charlie");
                dataStructures.setPrice(80);
                dataStructures.setDescription("Deep dive into essential data structures including arrays, linked lists, trees, graphs, and hash tables.");
                dataStructures.setP_link("https://img-c.udemycdn.com/course/750x422/1362070_b9a1_2.jpg");
                dataStructures.setY_link("https://youtu.be/8hly31xKli0?si=h0oIaP_HhfQqBNRw");
                courseRepository.save(dataStructures);

                // Machine Learning
                Course machineLearning = new Course();
                machineLearning.setCourse_name("Machine Learning");
                machineLearning.setInstructor("David");
                machineLearning.setPrice(120);
                machineLearning.setDescription("Introduction to machine learning algorithms, neural networks, and AI applications. Build intelligent systems from scratch.");
                machineLearning.setP_link("https://img-c.udemycdn.com/course/750x422/950390_270f_5.jpg");
                machineLearning.setY_link("https://youtu.be/i_LwzRVP7bg?si=QoKOExtCumWW8Gmb");
                courseRepository.save(machineLearning);

                // Java Full Stack
                Course javaFullStack = new Course();
                javaFullStack.setCourse_name("Java Full Stack");
                javaFullStack.setInstructor("Emma");
                javaFullStack.setPrice(100);
                javaFullStack.setDescription("Complete Java full-stack development course covering Spring Boot, React, REST APIs, and database integration.");
                javaFullStack.setP_link("https://img-c.udemycdn.com/course/750x422/827692_91ad_2.jpg");
                javaFullStack.setY_link("https://youtu.be/eIrMbAQSU34?si=qWzcNuwuqbqR4jAn");
                courseRepository.save(javaFullStack);

                // Additional domains
                
                // React.js
                Course react = new Course();
                react.setCourse_name("React.js Advanced");
                react.setInstructor("Frank");
                react.setPrice(90);
                react.setDescription("Master React.js with hooks, context API, Redux, and advanced patterns. Build scalable front-end applications.");
                react.setP_link("https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=800&h=400&fit=crop");
                react.setY_link("https://youtu.be/SqcY0GlETPk?si=eLrQrq4wng7u5339");
                courseRepository.save(react);

                // Node.js
                Course nodejs = new Course();
                nodejs.setCourse_name("Node.js Backend Development");
                nodejs.setInstructor("Grace");
                nodejs.setPrice(85);
                nodejs.setDescription("Learn Node.js, Express.js, and MongoDB. Build RESTful APIs and real-time applications.");
                nodejs.setP_link("https://images.unsplash.com/photo-1558494949-ef010cbdcc31?w=800&h=400&fit=crop");
                nodejs.setY_link("https://youtu.be/TlB_eWDSMt4?si=hhOfldi9iSwCceLR");
                courseRepository.save(nodejs);

                // Docker & Kubernetes
                Course docker = new Course();
                docker.setCourse_name("Docker & Kubernetes");
                docker.setInstructor("Henry");
                docker.setPrice(95);
                docker.setDescription("Containerization and orchestration with Docker and Kubernetes. Deploy scalable applications in production.");
                docker.setP_link("https://images.unsplash.com/photo-1605745341112-85968b19335b?w=800&h=400&fit=crop");
                docker.setY_link("https://youtu.be/Wf2eSG3owoA?si=kQUYpj2cCxHKZJdj");
                courseRepository.save(docker);

                // Cloud Computing (AWS)
                Course aws = new Course();
                aws.setCourse_name("AWS Cloud Computing");
                aws.setInstructor("Ivy");
                aws.setPrice(110);
                aws.setDescription("Master Amazon Web Services. Learn EC2, S3, Lambda, and cloud architecture patterns.");
                aws.setP_link("https://images.unsplash.com/photo-1451187580459-43490279c0fa?w=800&h=400&fit=crop");
                aws.setY_link("https://youtu.be/2OHr0QnEkg4?si=IF1CHRPRGZIPcPmW");
                courseRepository.save(aws);

                // Cybersecurity
                Course cybersecurity = new Course();
                cybersecurity.setCourse_name("Cybersecurity Fundamentals");
                cybersecurity.setInstructor("Jack");
                cybersecurity.setPrice(105);
                cybersecurity.setDescription("Learn ethical hacking, network security, cryptography, and secure coding practices.");
                cybersecurity.setP_link("https://images.unsplash.com/photo-1550751827-4bd374c3f58b?w=800&h=400&fit=crop");
                cybersecurity.setY_link("https://youtu.be/s19BxFpoSd0?si=2ahGSmmeGEAxEulN");
                courseRepository.save(cybersecurity);

                // Mobile App Development
                Course mobileDev = new Course();
                mobileDev.setCourse_name("Mobile App Development");
                mobileDev.setInstructor("Kate");
                mobileDev.setPrice(95);
                mobileDev.setDescription("Build iOS and Android apps using React Native and Flutter. Cross-platform mobile development.");
                mobileDev.setP_link("https://images.unsplash.com/photo-1512941937669-90a1b58e7e9c?w=800&h=400&fit=crop");
                mobileDev.setY_link("https://youtu.be/DsIviEKZad0?si=OcFKjJZCBhvu3BoX");
                courseRepository.save(mobileDev);

                // Full Stack Development with Java Spring Boot, React, and MongoDB
                Course fullStackJava = new Course();
                fullStackJava.setCourse_name("Full Stack Development with Java Spring Boot, React, and MongoDB");
                fullStackJava.setInstructor("Emma");
                fullStackJava.setPrice(150);
                fullStackJava.setDescription("Master full-stack development using Java Spring Boot for backend, React for frontend, and MongoDB for database. Build complete web applications from scratch.");
                fullStackJava.setP_link("https://img-c.udemycdn.com/course/750x422/827692_91ad_2.jpg");
                fullStackJava.setY_link("https://youtu.be/eIrMbAQSU34?si=qWzcNuwuqbqR4jAn");
                courseRepository.save(fullStackJava);

                // Spring Boot Framework
                Course springBoot = new Course();
                springBoot.setCourse_name("Spring Boot Framework");
                springBoot.setInstructor("Emma");
                springBoot.setPrice(110);
                springBoot.setDescription("Deep dive into Spring Boot framework. Learn dependency injection, REST APIs, Spring Security, and microservices architecture.");
                springBoot.setP_link("https://img-c.udemycdn.com/course/750x422/827692_91ad_2.jpg");
                springBoot.setY_link("https://youtu.be/eIrMbAQSU34?si=qWzcNuwuqbqR4jAn");
                courseRepository.save(springBoot);

                // React.js Complete Guide
                Course reactComplete = new Course();
                reactComplete.setCourse_name("React.js Complete Guide");
                reactComplete.setInstructor("Frank");
                reactComplete.setPrice(100);
                reactComplete.setDescription("Complete React.js course covering hooks, context API, Redux, React Router, and building production-ready applications.");
                reactComplete.setP_link("https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=800&h=400&fit=crop");
                reactComplete.setY_link("https://youtu.be/SqcY0GlETPk?si=eLrQrq4wng7u5339");
                courseRepository.save(reactComplete);

                // MongoDB Database Mastery
                Course mongoDB = new Course();
                mongoDB.setCourse_name("MongoDB Database Mastery");
                mongoDB.setInstructor("Grace");
                mongoDB.setPrice(90);
                mongoDB.setDescription("Master MongoDB NoSQL database. Learn data modeling, aggregation, indexing, and integration with Java Spring Boot applications.");
                mongoDB.setP_link("https://images.unsplash.com/photo-1544383835-bda2bc66a6d3?w=800&h=400&fit=crop");
                mongoDB.setY_link("https://www.youtube.com/watch?v=-56x56UppqQ");
                courseRepository.save(mongoDB);

                // Java Advanced Programming
                Course javaAdvanced = new Course();
                javaAdvanced.setCourse_name("Java Advanced Programming");
                javaAdvanced.setInstructor("Emma");
                javaAdvanced.setPrice(105);
                javaAdvanced.setDescription("Advanced Java concepts including multithreading, collections framework, streams API, lambdas, and design patterns.");
                javaAdvanced.setP_link("https://img-c.udemycdn.com/course/750x422/827692_91ad_2.jpg");
                javaAdvanced.setY_link("https://youtu.be/eIrMbAQSU34?si=qWzcNuwuqbqR4jAn");
                courseRepository.save(javaAdvanced);

                // RESTful API Development with Spring Boot
                Course restAPI = new Course();
                restAPI.setCourse_name("RESTful API Development with Spring Boot");
                restAPI.setInstructor("Emma");
                restAPI.setPrice(95);
                restAPI.setDescription("Build robust REST APIs using Spring Boot. Learn HTTP methods, JSON handling, authentication, and API best practices.");
                restAPI.setP_link("https://img-c.udemycdn.com/course/750x422/827692_91ad_2.jpg");
                restAPI.setY_link("https://youtu.be/eIrMbAQSU34?si=qWzcNuwuqbqR4jAn");
                courseRepository.save(restAPI);

                // React + Spring Boot Integration
                Course reactSpringIntegration = new Course();
                reactSpringIntegration.setCourse_name("React + Spring Boot Integration");
                reactSpringIntegration.setInstructor("Emma");
                reactSpringIntegration.setPrice(120);
                reactSpringIntegration.setDescription("Learn to connect React frontend with Spring Boot backend. Handle CORS, JWT authentication, and build complete applications.");
                reactSpringIntegration.setP_link("https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=800&h=400&fit=crop");
                reactSpringIntegration.setY_link("https://youtu.be/eIrMbAQSU34?si=qWzcNuwuqbqR4jAn");
                courseRepository.save(reactSpringIntegration);

                // Spring Security
                Course springSecurity = new Course();
                springSecurity.setCourse_name("Spring Security");
                springSecurity.setInstructor("Emma");
                springSecurity.setPrice(100);
                springSecurity.setDescription("Implement security in Spring Boot applications. Learn JWT, OAuth2, authentication, authorization, and securing REST APIs.");
                springSecurity.setP_link("https://img-c.udemycdn.com/course/750x422/827692_91ad_2.jpg");
                springSecurity.setY_link("https://youtu.be/eIrMbAQSU34?si=qWzcNuwuqbqR4jAn");
                courseRepository.save(springSecurity);

                // TypeScript for React
                Course typescriptReact = new Course();
                typescriptReact.setCourse_name("TypeScript for React");
                typescriptReact.setInstructor("Frank");
                typescriptReact.setPrice(85);
                typescriptReact.setDescription("Use TypeScript with React for type-safe frontend development. Learn interfaces, types, and best practices.");
                typescriptReact.setP_link("https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=800&h=400&fit=crop");
                typescriptReact.setY_link("https://youtu.be/SqcY0GlETPk?si=eLrQrq4wng7u5339");
                courseRepository.save(typescriptReact);

                // MySQL Database with Spring Boot
                Course mysqlSpring = new Course();
                mysqlSpring.setCourse_name("MySQL Database with Spring Boot");
                mysqlSpring.setInstructor("Emma");
                mysqlSpring.setPrice(95);
                mysqlSpring.setDescription("Learn Spring Data JPA, Hibernate, and MySQL integration. Master database operations and relationships.");
                mysqlSpring.setP_link("https://images.unsplash.com/photo-1544383835-bda2bc66a6d3?w=800&h=400&fit=crop");
                mysqlSpring.setY_link("https://youtu.be/eIrMbAQSU34?si=qWzcNuwuqbqR4jAn");
                courseRepository.save(mysqlSpring);

                // Microservices with Spring Boot
                Course microservices = new Course();
                microservices.setCourse_name("Microservices with Spring Boot");
                microservices.setInstructor("Emma");
                microservices.setPrice(130);
                microservices.setDescription("Build microservices architecture using Spring Boot, Spring Cloud, Eureka, and API Gateway. Scalable application design.");
                microservices.setP_link("https://img-c.udemycdn.com/course/750x422/827692_91ad_2.jpg");
                microservices.setY_link("https://youtu.be/eIrMbAQSU34?si=qWzcNuwuqbqR4jAn");
                courseRepository.save(microservices);

                // Redux State Management
                Course redux = new Course();
                redux.setCourse_name("Redux State Management");
                redux.setInstructor("Frank");
                redux.setPrice(90);
                redux.setDescription("Master Redux for React applications. Learn state management, middleware, Redux Toolkit, and async operations.");
                redux.setP_link("https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=800&h=400&fit=crop");
                redux.setY_link("https://youtu.be/SqcY0GlETPk?si=eLrQrq4wng7u5339");
                courseRepository.save(redux);

                // GraphQL with Spring Boot
                Course graphql = new Course();
                graphql.setCourse_name("GraphQL with Spring Boot");
                graphql.setInstructor("Emma");
                graphql.setPrice(100);
                graphql.setDescription("Build GraphQL APIs with Spring Boot GraphQL. Learn schema design, queries, mutations, and subscriptions.");
                graphql.setP_link("https://img-c.udemycdn.com/course/750x422/827692_91ad_2.jpg");
                graphql.setY_link("https://youtu.be/eIrMbAQSU34?si=qWzcNuwuqbqR4jAn");
                courseRepository.save(graphql);

                // Next.js Full Stack
                Course nextjs = new Course();
                nextjs.setCourse_name("Next.js Full Stack Development");
                nextjs.setInstructor("Frank");
                nextjs.setPrice(110);
                nextjs.setDescription("Build full-stack applications with Next.js. Server-side rendering, API routes, and React best practices.");
                nextjs.setP_link("https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=800&h=400&fit=crop");
                nextjs.setY_link("https://youtu.be/SqcY0GlETPk?si=eLrQrq4wng7u5339");
                courseRepository.save(nextjs);

                // Spring Boot Testing
                Course springTesting = new Course();
                springTesting.setCourse_name("Spring Boot Testing");
                springTesting.setInstructor("Emma");
                springTesting.setPrice(85);
                springTesting.setDescription("Master testing in Spring Boot applications. Unit tests, integration tests, MockMvc, and TestContainers.");
                springTesting.setP_link("https://img-c.udemycdn.com/course/750x422/827692_91ad_2.jpg");
                springTesting.setY_link("https://youtu.be/eIrMbAQSU34?si=qWzcNuwuqbqR4jAn");
                courseRepository.save(springTesting);

                // React Hooks & Context API
                Course reactHooks = new Course();
                reactHooks.setCourse_name("React Hooks & Context API");
                reactHooks.setInstructor("Frank");
                reactHooks.setPrice(80);
                reactHooks.setDescription("Deep dive into React Hooks (useState, useEffect, useContext, useReducer) and Context API for state management.");
                reactHooks.setP_link("https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=800&h=400&fit=crop");
                reactHooks.setY_link("https://youtu.be/SqcY0GlETPk?si=eLrQrq4wng7u5339");
                courseRepository.save(reactHooks);

                    log.info("Successfully initialized {} courses.", courseRepository.count());
                } else {
                    log.info("Courses already exist ({} courses found), skipping initialization.", existingCount);
                }
            } catch (Exception e) {
                log.error("Error during course initialization: ", e);
            }
        };
    }
}

