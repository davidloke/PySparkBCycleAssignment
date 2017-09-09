# PySparkBCycleAssignment
Analytics Use Cases for B-Cycle in Austin, TX - Assignment for CBG1C04 Big Data Programming (Temasek Polytechnic)

Architecture: Jupyter Notebook interfacing with a pseudo distributed Apache Spark cluster using PySpark. 

Datasets not included.

Description of files: 
1) "Assignment Notebook.ipynb"
Jupyter Nodebook that implements the assignment using PySpark. 

2) "AddSyntheticCustInfo.java" 
Simple Java program that adds synthetic customer data to the B-Cycle Trips Data Set records. For Use Case #4

3) "processTrips.py"
Simple Python program that processes B-Cycle trips records, and calculates the remaining capacity of each kiosk. 
When a kiosk has 0 capacity, a message will be sent via the socket. For Use Case #2.
