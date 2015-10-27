#! /bin/bash
bin/lenskit recommend -m $1 -n 10 --ratings-file $2 -d '::' $3 | sed 's/://g' | awk '{print $1" "$2}' | sort -k 1b,1 | join - data/u.itemnames | sort -n -k 2 -r
