# Information Systmes Lab 2018

The repository contains the project for the Information Systems Lab 2018.


## Running the app

Run the following commands on Windows, MacOS, GNU/Linux:

1) Run *gradlew jar* in the root directory. The command will produce *infosyslab-rest-service-0.1.0.jar* in the *libs* directory. 

2) Run the jar file with *java -jar infosyslab-rest-service-0.1.0.jar*.

3) The application will start on localhost:8000. Go to the browser to see the app.


## Rest API documentation:

The API is fully independent of the front end part. It can be also used to create a native smartphone app in the future.

### **Start the session**

POST Request: 

**URL**:

localhost:8000/api

**Instructions**:

Set the header **Content-Type application/json**

Set **filter** either to **"recent"** or **"popular"**

Set **numvalue** to -1, or 1, 2, ..., N

**Request Body**

```
{
	"profile1": "ivan.tishchenko888",
	"profile2": "moira_photography"
	"filter": "popular"
	"numvalue": -1
}
```

**JSON Response**: 

Returns an ID, which you use later.

Use the acquired ID as a GET query param **id**

### **Followers endpoints:**

#### Ratio

Get the followe to followee ratio

GET Request: 

**URL:**

localhost:8000/api/follower/ratio?id=**ID_FROM_FIRST_STEP**

**JSON Response:**

```
{
    "followCount1": 16,
    "followCount2": 1428,
    "followedByCount1": 108,
    "followedByCount2": 15108,
    "ratio1": 6.75,
    "ratio2": 10.579832
}
```

### **Hashtags endpoints:**

#### Hashtag percentage

Get 5 most popular hashtags and their percentages relative to number of posts

GET Request: 

**URL:**

localhost:8000/api/hashtags/percentage?id=**ID_FROM_FIRST_STEP**

**JSON Response:**

```
{
    "hashtags1": {
        "switzerland????": 63.636364,
        "mountains": 54.545456,
        "zurich": 36.363636,
        "suisse": 27.272728,
        "clouds": 27.272728
    },
    "hashtags2": {
        "animalelite": 84,
        "shots_of_animals": 77,
        "africanamazing": 71,
        "nature": 60,
        "splendid_animals": 59
    }
}
```

#### Hashtags to Posts distribution

Get 2 distributions. Number of bins correspond to number of tags in a post 0 ... 30. Bins values correspond to number of posts containing that amount of tags. 

GET Request: 

**URL:**

localhost:8000/api/hashtags/distribution?id=**ID_FROM_FIRST_STEP**

**JSON Response:**

```
{
    "distribution1": [
        1,
        0,
        0,
        2,
        2,
        1,
        2,
        2,
        2,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
    ],
    "distribution2": [
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        1,
        0,
        0,
        0,
        0,
        0,
        1,
        0,
        0,
        0,
        0,
        1,
        2,
        1,
        2,
        4,
        2,
        6,
        5,
        5,
        10,
        8,
        11,
        41
    ]
}
```

#### Distribution of avg # of likes over hashtags

Returns 2 distributions of 10 tags each.

GET Request: 

**URL:**

localhost:8000/api/hashtags/tags-likes-distribution?id=**ID_FROM_FIRST_STEP**

**JSON Response:**

```
{
    "distribution1": {
        "HyperCar": 120455,
        "Hypercar": 156115,
        "AMGShowroom": 123267,
        "GT53": 138215,
        "Interior": 144266,
        "becauseracecar": 156115,
        "ProjectONE": 126179,
        "DrivingPerfomance": 156115,
        "nuerburgring": 156115,
        "showcar": 156115
    },
    "distribution2": {
        "spring": 31,
        "sky": 33,
        "foggy": 31,
        "see": 30,
        "mountains": 30,
        "valley": 30,
        "höngg": 31,
        "peaks": 30,
        "sunny??": 32,
        "colors": 30
    }
}
```

#### Distribution of avg # of comments over hashtags

Returns 2 distributions of 10 tags each.

GET Request: 

**URL:**

localhost:8000/api/hashtags/tags-comments-distribution?id=**ID_FROM_FIRST_STEP**

**JSON Response:**
```
{
    "distribution1": {
        "Hypercar": 398,
        "HyperCar": 412,
        "GT53": 340,
        "AMGShowroom": 350,
        "becauseracecar": 398,
        "Interior": 414,
        "SportscarTogether": 326,
        "DrivingPerfomance": 398,
        "nuerburgring": 398,
        "showcar": 398
    },
    "distribution2": {
        "suisse": 0,
        "zurich": 0,
        "mountains": 0,
        "see": 2,
        "predator": 1,
        "cat": 1,
        "swiss": 1,
        "valley": 2,
        "peaks": 2,
        "colors": 2
    }
}
```

### **Userinfo endpoints:**

#### User infromation

Returns 2 flags indicated profiles' privacy setting, returns 2 url for the profile pics

GET Request: 

**URL:**

localhost:8000/api/userinfo?id=**ID_FROM_FIRST_STEP**

**JSON Response:**

```
{
    "fullName1": "Ivan Tishchenko",
    "fullName2": "Moira Norrie",
    "privacyFlag1": false,
    "privacyFlag2": false,
    "picUrl1": "https://scontent-amt2-1.cdninstagram.com/vp/62630e773184d6abe97bd0871f806bb0/5B4ED76C/t51.2885-19/s150x150/12519374_590894204420769_1814533297_a.jpg",
    "picUrl2": "https://scontent-amt2-1.cdninstagram.com/vp/129ff9005072aee339bf6720a8a39a29/5B60C728/t51.2885-19/s150x150/14269182_1134201039999844_343560398_a.jpg"
}
```

#### Top 3 most popular posts

Returns detailed information for 2 users about top 3 posts.

GET Request: 

**URL:**

localhost:8000/api/userinfo/top3-posts?id=**ID_FROM_FIRST_STEP**

**JSON Response:**

```
{
    "captions1": [
        "Havin' a good time \n#bobsburgers #augsburg #santa muerte #beer #cafe",
        "Night's Blur\n#augsburger sommernächte #deephouse #festival #augsburg",
        "Welcoming The Summer ????????\n#erdbeertorte #yummy #baking"
    ],
    "captions2": [
        "",
        "#sky #nature #hiking #mountains #switzerland???? #sunny?? #zurich",
        "#mountains #suisse #switzerland"
    ],
    "likes1": [
        17,
        17,
        17
    ],
    "likes2": [
        44,
        33,
        33
    ],
    "comments1": [
        0,
        0,
        1
    ],
    "comments2": [
        1,
        0,
        1
    ],
    "dates1": [
        "Sat Sep 2 2017 18:13:14 +0200",
        "Fri Jun 30 2017 23:20:56 +0200",
        "Sat Jun 17 2017 7:16:28 +0200"
    ],
    "dates2": [
        "Mon Mar 28 2016 11:20:28 +0200",
        "Fri Feb 23 2018 16:52:14 +0100",
        "Fri Feb 23 2018 9:27:58 +0100"
    ],
    "urls1": [
        "https://scontent-frt3-2.cdninstagram.com/vp/f2fb3181ef0fc2653e81027c1d1bc536/5B78A085/t51.2885-15/s640x640/sh0.08/e35/c0.97.1080.1080/26073008_1939805306280835_8094123962486751232_n.jpg",
        "https://scontent-frt3-2.cdninstagram.com/vp/105c3a48b11f3f1d29fcb1b2439df6e3/5B810973/t51.2885-15/s640x640/sh0.08/e35/31198267_360580421097495_5860677733763252224_n.jpg",
        "https://scontent-frt3-2.cdninstagram.com/vp/bfad24bb535eba59d1f61eb5f3654a5c/5B89D18F/t51.2885-15/s640x640/sh0.08/e35/c0.135.1080.1080/29417308_211561959592437_231820138247094272_n.jpg"
    ],
    "urls2": [
          "https://scontent-frt3-2.cdninstagram.com/vp/9edff730b328facbac44e28046f65b12/5B8BE019/t51.2885-15/s640x640/sh0.08/e35/c0.135.1080.1080/21372015_1435564133463859_7095765029349228544_n.jpg",
          "https://scontent-frt3-2.cdninstagram.com/vp/6dcc9ed6550e023897282b55d0fa9918/5B7A0FBB/t51.2885-15/s640x640/sh0.08/e35/18889012_1568744026490118_442865639942520832_n.jpg",
          "https://scontent-frt3-2.cdninstagram.com/vp/63bc16de77fd8e1b7487df1ad1fe9efe/5B91451B/t51.2885-15/s640x640/sh0.08/e35/18013120_1319496004802832_6022623186510675968_n.jpg"
    ]
}
```

### **Posting time endpoints:**

#### Histogram time distribution

Get 2 histograms of 24 hours posting times distribution 

GET Request: 

**URL:**

localhost:8000/api/posttime/histogram?id=**ID_FROM_FIRST_STEP**

**JSON Response:**

```
{
    "distribution1": [
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        3,
        0,
        1,
        0,
        0,
        0,
        0,
        2,
        0,
        0,
        3,
        2,
        0,
        0,
        0
    ],
    "distribution2": [
        0,
        0,
        0,
        0,
        0,
        1,
        6,
        28,
        36,
        21,
        3,
        4,
        0,
        0,
        0,
        1,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
    ]
}
```
#### Weekly time distribution

Get 2 histograms of 7 day posting times distribution 

GET Request: 

**URL:**

localhost:8000/api/posttime/week?id=**ID_FROM_FIRST_STEP**

**JSON Response:**

```
{
    "distribution1": [
        2,
        1,
        2,
        0,
        0,
        6,
        1
    ],
    "distribution2": [
        15,
        12,
        9,
        13,
        18,
        18,
        15
    ]
}
```

#### Posting time regularity

Get 2 histograms: 
 1. postPerWeeks: [ numberOfPosts, howManyWeeks ]
 2. postsPerDay: [ numberOfPosts, howManyDays ]

GET Request: 

**URL:**

localhost:8000/api/posttime/regularity?id=**ID_FROM_FIRST_STEP**

**JSON Response:**

```
{
  "postsPerWeek1": [
    [
      1,
      10
    ],
    [
      2,
      6
    ],
    [
      3,
      3
    ],
    [
      4,
      6
    ],
    [
      5,
      2
    ],
    [
      6,
      1
    ],
    [
      7,
      3
    ],
    [
      8,
      1
    ]
  ],
  "postsPerWeek2": [
    [
      1,
      41
    ],
    [
      2,
      13
    ],
    [
      3,
      8
    ],
    [
      4,
      1
    ],
    [
      5,
      1
    ]
  ],
  "postsPerDay1": [
    [
      1,
      72
    ],
    [
      2,
      8
    ],
    [
      3,
      4
    ]
  ],
  "postsPerDay2": [
    [
      1,
      84
    ],
    [
      2,
      2
    ],
    [
      3,
      4
    ]
  ]
}
```

### **Advice endpoints:**

#### Posting time advice

Get an advice for posting time. If STD of a more successful profile is less returns an advice, otherwise returns an empty string.

GET Request: 

**URL:**

localhost:8000/api/advice/time?id=**ID_FROM_FIRST_STEP**

**JSON Response:**

```
{
    "advice": "Be more coherent in posting. Try to post the same amount of posts every day"
}
```


### **Ratio endpoints:**

#### Likes to Follower Ratio

Get a Avg#Likes / #Followers Ratio

GET Request: 

**URL:**

localhost:8000/api/ratio/like2follower?id=**ID_FROM_FIRST_STEP**

**JSON Response:**

```
{
    "ratio1": 0.23433048433048434,
    "ratio2": 0.09635425317356744
}
```

#### Comments to Follower Ratio

Get a Avg#Comments / #Followers Ratio

GET Request: 

**URL:**

localhost:8000/api/ratio/comment2follower?id=**ID_FROM_FIRST_STEP**

**JSON Response:**

```
{
    "ratio1": 0.0035612535612535613,
    "ratio2": 0.003729386641357219
}
```

#### Video vs Image Ratios

Get video vs image ratios

GET Request: 

**URL:**

localhost:8000/api/ratio/video-image-avg?id=**ID_FROM_FIRST_STEP**

**JSON Response:**

```
{
    "imagesSize1": 86,
    "imagesSize2": 12,
    "avgLikesImage1": 6.872093023255814,
    "avgLikesImage2": 27.5,
    "avgCommentsImage1": 0.4186046511627907,
    "avgCommentsImage2": 0.4166666666666667,
    "videosSize1": 8,
    "videosSize2": 0,
    "avgLikesVideo1": 8.125,
    "avgLikesVideo2": 0,
    "avgCommentsVideo1": 0.875,
    "avgCommentsVideo2": 0
}
```