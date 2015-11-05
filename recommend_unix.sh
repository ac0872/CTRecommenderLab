#! /bin/bash
bin/lenskit recommend -c $1 -n 10 --csv-file $2 $3 --item-names data/movies.csv | sed 's/://g' | awk '{print $1" "$2}' | sort -k 1b,1 | join - data/u.itemnames | sort -n -k 2 -r
