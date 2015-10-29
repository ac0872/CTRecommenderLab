#!/bin/bash
user_id=6042
movies_file=data/movies.csv
ratings_file=data/ratings.csv
separator=","
rated=0
rm "data/personal_ratings.dat"
for movie in `awk -F"$separator" '{print $2}' $ratings_file | sort | uniq -c | sort -n -r | awk '{print $2}'` 
do
	rating=-2
	title=$(awk -F"$separator" -v movie=$movie '$1==movie{print $2}' $movies_file)
	while [ $rating -lt -1 -o $rating -gt 5 ]; do
		echo "You have rated a total of $rated movies"
		echo "Please rate $title. Give 1, 2, 3, 4 or 5 stars (0 if you do not know the movie)"
		echo "Type -1 you want to quit inputting ratings"
		read rating
	done
	if [ $rating -eq -1 ]
	then
		cat data/personal_ratings.dat >> $ratings_file
		break
	fi
	if [ $rating -ne 0 ]
	then
		ts=$(date +%s)
		echo "$user_id$separator$movie$separator$rating$separator$ts" >> data/personal_ratings.dat
		rated=$((rated+1))
	fi    
done