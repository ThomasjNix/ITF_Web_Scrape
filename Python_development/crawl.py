
from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup

###################################Import the TennisPlayers.txt file###################################
readFile = open('TennisPlayers.txt', 'r')

###################################Run crawl function against each URL (each line)###################################
#parse HTML information on the page
#gather pertinent information on each player, seperating out blank pages (or leaving them and using empty values in our model)
#save all the data gathered in a URL as a single line of values (in a specific order) in an array

#Example
placeholder_content_array = ['placeholder,placeholder,placeholder','placeholder2,placeholder2,placeholder2','placeholder3,placeholder3,placeholder3']
###################################Output the data in CSV format to a new file, TennisPlayers.csv###################################


#Open a CSV file for writing, seperate each element in the array [instances], write to the CSV file with new line seperation
csv_writer = open('TennisPlayers.csv','w')
for instance_information in placeholder_content_array:
    csv_writer.write(instance_information+'\n')
csv_writer.close()
