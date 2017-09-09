import socket
import time 

# Read in the kiosks and set the initial capacity to be 5 (assumption made) 
allKiosks = {}
with open('/home/training/assignmentdata/streamingFolder/kiosk/part-00000', 'r') as f:
    for line in f:
        lineTokens = line.split(',')
        allKiosks[lineTokens[0]] = 5

# Listen on the socket for Spark to connect
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
s.bind(('127.0.0.1', 8586))
print("Going to listen now...")
s.listen(1)
conn, addr = s.accept()
print 'Connection:', addr

    
# Credit: https://wiki.python.org/moin/TcpCommunication
def alertNoSpace(kiosk_id,space): 
    if(space == 0):
		# Send message over the socket 
        conn.send('B-Cycle kiosk ' + kiosk_id + ' has reached maximum capacity\n' )

# Process each trip at a rate of 1 record per second. (Take a sample for presentation purposes)
with open('/home/training/assignmentdata/SampleStreamingTripsData', 'r') as ff:
     for line in ff:
        lineTokens = line.split(',')
		
		# +1 capacity for the checkout kiosk, bounded at 13
        if (allKiosks.has_key(lineTokens[5])):
            allKiosks[lineTokens[5]] = allKiosks[lineTokens[5]] + 1
            if(allKiosks[lineTokens[5]] > 13): 
				allKiosks[lineTokens[5]] = 13
           
		# -1 capacity for the checkin kiosk, bounded at 0
        if (allKiosks.has_key(lineTokens[7])):
            allKiosks[lineTokens[7]] = allKiosks[lineTokens[7]] - 1
            if(allKiosks[lineTokens[7]] < 0): 
				allKiosks[lineTokens[7]] = 0
            
			# Alert the Spark program if the capacity is 0
			alertNoSpace(lineTokens[7], allKiosks[lineTokens[7]])
		
		# Regulate the speed of processing 
        time.sleep(1)
        
# Print final state of all kiosks 
print(allKiosks)

# Close the socket 
s.close()