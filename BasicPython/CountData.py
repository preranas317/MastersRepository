import urllib
import re
timecount = seasoncount = 0

link = "https://raw.githubusercontent.com/csula/cs594-spring-2015/master/Syllabus.md"
f = urllib.urlopen(link)
myfile = f.read()

yearcount = 0

line1 = myfile.split('\n')

line = myfile.split(' ')

daycount = line.count("Sunday") + line.count("Monday") + line.count("Tuesday") + line.count("Wednesday") + line.count("Thursday") + line.count("Friday") + line.count("Saturday") 

for i in line:
    #print(i)
    if ('spring' in i or 'fall' in i or 'summer' in i or 'winter' in i):
        seasoncount = seasoncount+1
    if 'AM' in i:
        timecount = timecount+1
    if 'PM' in i:
        timecount = timecount+1
     

dates =[]
months = []
years = []
datecount = 0
yc = []
ycc = []
for i in line1:
    if re.findall(r"\b(\d{4})\b", i):
        yc.append(re.findall(r"\b(\d{4})\b", i))
       
            
    if(i.startswith("* **")):
        datecount = datecount + 1;
        temp = i.split('/')
        yeartemp = temp[2].split("**")
        years.append(yeartemp[0])
        months.append(temp[1])
        for j in temp:
            if(j.startswith("* **")):
                date = j.split("* **")
                dates.append(date[1])
         
for p in yc:
    for l in p:
        if(int(l)>=2000 and int(l)<=2015):
            ycc.append(l)   



print "Seasons: " +str(seasoncount)
print "Year: "+str(len(ycc))
print "Month: "+str(len(months))
print "Day Of the Week: "+str(daycount)
print "Time of the Day: "+str(timecount)
print "Date: "+str(len(dates))


