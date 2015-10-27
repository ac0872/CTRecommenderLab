#! /bin/bash
bin/lenskit train-model $1 --ratings-file $2 -d '::' -o $3
