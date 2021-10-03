# README
To build use:
`mvn package`

To run use:
`./run`

To start the server use:
`./run --gui [--port=<port>]`
---
# Project Details
Project Name: Project 1 - Data \
Project Members and Contribution: \
Total Time Spent: \
---
## ORM Tests
First `database path/to/database` must be run to create a database connection. 
#### We ran the following commands to add 3 users, 2 reviews, and 2 sets of rental info to the empty database:
`insert 1 123lbs 34c 5'10" 32 pear Libra` \
`insert 2 143lbs 32b 5'5" 22 athletic Aquarius` \
`inset 3 123lbs 30b 6'1" 27 hourglass Leo` \
`insert 1 large 1 1 7 everyday shirt 12` \
`insert 2 fit 2 2 3 vacation dress 14 2` \
``insert 1 `This is a great shirt!` `Not bad!` `October 1, 2021` `` \
``insert 2 `This dress is great!` `Great dress` `September 13, 2020` `` 
#### We tested delete by running the following commands: 
``delete 1 `This is a great shirt!` `Not bad!` `October 1, 2021` `` \
`delete 1 large 1 1 7 everyday shirt 12` 
#### We tested where by running the following commands: 
`select Users horoscope=? Leo` which returned `[User with id: 3]` 
#### We then tested update by running the following commands: 
`update 1 123lbs 34c 5'10" 32 pear Libra horoscope Leo` \
`update 2 143lbs 32b 5'5" 22 athletic Aquarius weight 147lbs` \
`update 2 143lbs 32b 5'5" 22 athletic Aquarius height 5'7"` \
After that we ran the earlier select command and got back: \
`[User with id: 1, User with id: 3]` 
#### We tested the sql method by running the following commands: 
``sql `SELECT COUNT(*) FROM users` `` which returned 3. \
``sql `SELECT * FROM users WHERE horoscope='Leo'` `` which returned 1, 123lbs, 34c, 5'10", 32, pear, Leo
and 3, 123lbs, 30b, 6'1", 27, hourglass, Leo. \
``sql `SELECT fit FROM rent WHERE id=1` `` which returned nothing as expected. \
``sql `SELECT fit FROM rent WHERE id=2` `` which returned fit. \
``sql `UPDATE rent SET rating='9' WHERE id=2` `` which correctly updated the rating to 9.