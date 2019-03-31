**How to run the Program**

1: IDEA: to run Intellij or eclipse you would need to import the project as gradle and run it 
2: Gradle: the project uses gradle is building tool, you can use gradle commands to run the project 
{gradle clean build} {gradle bootRun}

**Using Post man**
<ol type="1">
  <li>Post Method
   <ul>
      <li> Create only hotel without rooms and amenities</li>
      
        {
        	"hotelName" : "Juba Hotel 11",
        	"description": "Located in Jumairah",
        	"cityCode": "W04"
        }
   </ul>
   
   <ul>
      <li> Create only hotel without rooms and without amenities</li>
         
           {
               "hotelName": "Juba Hotel 12",
               "description": "Located in Jumairah",
               "cityCode": "W04",
               "rooms": [
                   {
                       "description": "Big room with double bed"
                   }
                   
               ]
           }
   </ul>
 
  <ul>
       <li> Create only hotel without rooms and with room amenities</li>
          
            {
                "hotelName": "Juba Hotel 14",
                "description": "Located in Jumairah",
                "cityCode": "W04",
                "rooms": [
                    {
                        "description": "Big room with double bed",
                        "roomAmenities": [
                            {
                                "amount": 87,
                                "chargeable": true,
                                "amenityMst": {"amenityId": 1}
                            }
                        ]
                    }
                ]
            }
   </ul>
    
   <ul>
          <li> Create hotel with hotelAmenities and with rooms and with RoomAmenities</li>
             
               {
                   "hotelName": "Juba Hotel 18",
                   "description": "Located in Jumairah",
                   "cityCode": "W04",
                   "hotelAmenities": [
                       {
                           "description": "Swimming pool",
                           "amenityMst": {
                               "amenityId": 1
                           }
                       }
                   ],
                   "rooms": [
                       {
                           "description": "Big room with double bed",
                           "roomAmenities": [
                               {
                                   "amount": 87,
                                   "chargeable": true,
                                   "amenityMst": {
                                       "amenityId": 1
                                   }
                               }
                           ]
                       }
                   ]
               }
   </ul> 
  </li>
  
</ol>  

**Pending items**
<ul>
<li>The `batch processing` functionality is very basic, it will only process and writes hotel fields into the database.
 This functionality can be improved by adding validation, checks and creating our own mapping by implementing LineMapper<T>.
</li>
<li>More unite cases and testing is needed </li>
<li> JPA tuning by adding @NamedParameter and pagination </li>
</ul>

**Design consideration for better performance**

1: Speedment: I will definitely try using Speedment , for more information please see this
 <a href="https://dzone.com/articles/the-need-for-speed-access-existing-data-1000x-fast">link </a> 

2: JPA tuning: Caching, pagination, limit and skip, using fetch lazy instead of eager fetching (fetch=FetchType.LAZY), @NamedQuery
