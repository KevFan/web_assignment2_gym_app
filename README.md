# Web Assignment 2 - Play Framework Gym Applet

### Purpose Of Project:
The project uses the play framework to develop a gym applet to allows member's of a gym to register and login to access their list of assessments if any, to add assessments and to generate some simple analytics such as BMI.

A trainer can also log in, view a list of members he manages, update comment for assessments and delete members.

### How to start project:
The project is build using play framework 1.4.4. The project has not been tested in any other play framework versions.

### User Instructions:
To start the project, download/clone the repository and run the play run command at the project directory.

Alternative, if the project is deployed, you can visit <link>   

Note: Website is intended for use on a desktop - some functionality such as change in opacity on hover or pop up text on hover may not work as intended when viewed on a mobile system

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
+ Trainer can delete member assessments but this logs the trainer out afterwards due to view redirection in the delete assessments method
+ Trainer can only view members that he/she manages, therefore newly registered member's that are not assigned to a trainer cannot be viewed
+ There is no sign up available for a Trainer

### List of Software + Technologies Used
+ Play Framework 1.4.4
+ Heroku (if deployed)


### Authors:
Kevin Fan


### Version/Date:
16th May 2017

