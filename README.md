# Web Assignment 2 - Play Framework Gym Applet

### Project Title:
Play Framework Gym Applet - https://github.com/KevFan/web_assignment2_gym_app

### Purpose Of Project:
The project uses the play framework to develop a gym applet to allows member's of a gym to register and login to access their list of assessments if any, to add assessments and to generate some simple analytics such as BMI.

A trainer can also log in, view a list of members he manages, update comment for assessments and delete members.

### How to start project:
The project is build using play framework 1.4.4. The project has not been tested in any other play framework versions.

The project currently uses the deployed platform and as such, can't be run locally without applying these changes to conf/application.conf: (line 87 - 91)

```
db.default=mem

# db=${DATABASE_URL}
# jpa.dialect=org.hibernate.dialect.PostgreSQLDialect
# jpa.ddl=update
```

### User Instructions:
To start the project, download/clone the repository and run the play run command at the project directory.

Alternative, if the project is deployed, you can visit https://play-gym.herokuapp.com/ 

### Feature List:
+ Session information
+ Assessments - sorted by date
+ Member - registration
+ Member - add assessments
+ Member - delete assessments
+ Member - update account details
+ Trainer - update assessment comment
+ Trainer - delete member

### Notes: 
+ There is no sign up available for a Trainer
+ Trainer acts as the admin, which views all members in the database (Code is also provided, for if trainers can only 
view and delete members they manage, however currently there is no option for a trainer to add members to this list, 
and as such newly registered members can't be viewed by the trainer)

### List of Software + Technologies Used
+ Play Framework 1.4.4
+ Heroku - Deployment platform
+ SonarLint - Java code review plugin


### Authors:
Kevin Fan


### Version/Date:
16th May 2017

