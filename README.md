# SAP-JCO-Connector
Setting Up Of SAP Connection using SAP JCO3 in Eclipse IDE
One Can Setup SAP Application connection with Java Application using below steps:

Steps to Produce:

1.	Download SAP Java connectors SAPJCO3 (32bit or 64bit based on your System architecture) from SAP Marketplace.
2.	Create a separate folder and keep the downloaded sapjco3 zip file and unzip it.
3.	Copy the location of sapjco3.jar file in the newly created folder.
4.	Now go to Environment Variables and create system variables CLASSPATH if not exist and add location of sapjco3.jar followed by ; ex: D:\sapjco3-NTAMD64-3.0.16\sapjco3.jar;
5.	Edit System Variable PATH and add newly created folder location followed by ; ex: D:\sapjco3-NTAMD64-3.0.16;
6.	Now Go to Eclipse and Create a new project.
7.	Create a new Class with any name for connecting to SAP application.
8.	Right Click on Newly created project and go to build path and click Configure Build Path.
9.	Click on Libraries and Add External Jars.
10.	Now select sapjco3.jar file just downloaded.
11.	Make your class name as same as you created in step 7. 
12. Get the code in newly created java file available in the repository.

Help:
For any queries mail: krishnamurarihyb@gmail.com
