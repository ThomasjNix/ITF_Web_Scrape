
from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import requests

###################################Import the TennisPlayers.txt file###################################
link_array = []
readFile = open('TennisPlayers.txt', 'r')
while True:
    inLine = readFile.readline()
    if not inLine:
        break
    else:
        link_array.append(inLine)
###################################Loop through each link###################################

test_writer = open('test-file.txt', 'w')
index = 0
placeholder_content_array = []
while (index<len(link_array)):
###################################Run crawl function against each URL (each line)###################################
    url = link_array[index]
    request_object  = requests.get(url)
    parsed_html = request_object.text

    BSObject = soup(parsed_html, 'lxml')
    playerName = BSObject.findAll('h1', class_="hBg")
    print(playerName)

    #if (len(BSObject.findAll("h1", class_="hBg"))>0):
    #    test_writer.write(BSObject.findAll("h1", class_="hBg")[0])
    #    print(BSObject.findAll("h1", class_="hBg"))
    index += 1
#parse HTML information on the page
#gather pertinent information on each player, seperating out blank pages (or leaving them and using empty values in our model)
#save all the data gathered in a URL as a single line of values (in a specific order) in an array
#Example

###################################Output the data in CSV format to a new file, TennisPlayers.csv###################################
#Open a CSV file for writing, seperate each element in the array [instances], write to the CSV file with new line seperation
csv_writer = open('TennisPlayers.csv','w')
for instance_information in placeholder_content_array:
    csv_writer.write(instance_information+'\n')
csv_writer.close()
