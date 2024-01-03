maxTime=$(($1 + 0))

problemNum=$2

judgementNum=$3

configPath=$4

echo $configPath

line=`cat $4`
#line=`cat /Users/liyinuo/IdeaProjects/HebeuOJ-SpringBoot/src/main/resources/judge.conf`
absoluteInOutFilePath=`echo $line|awk -F "|" '{print $1}'`
absoluteJudgementFilePath=`echo $line|awk -F "|" '{print $2}'`

#echo $datadir
#echo $line

#echo $password
#echo $remote_path

# shellcheck disable=SC1090
source ~/.bash_profile #啊
mkdir -p "$absoluteJudgementFilePath"/"$judgementNum"/out
mkdir -p "$absoluteJudgementFilePath"/"$judgementNum"/ans

# shellcheck disable=SC2164
# shellcheck disable=SC2086
cd "$absoluteJudgementFilePath"/$judgementNum

for i in {1..114514} ; do
    if [ ! -f $absoluteInOutFilePath/$problemNum/data/$i.in ]; then
        break
    fi
    start=$(date "+%Y%m%d%H%M%S")$((`date +%N`/1000000))

    gtimeout 2s ./main < $absoluteInOutFilePath/$problemNum/data/$i.in > $absoluteJudgementFilePath/$judgementNum/out/$i.out
    if [ $? != 0 ]; then
        echo "#&#$i TLE 3000"
    else
      end=$(date "+%Y%m%d%H%M%S")$((`date +%N`/1000000))

        diff -w $absoluteInOutFilePath/$problemNum/data/$i.out $absoluteJudgementFilePath/$judgementNum/out/$i.out > $absoluteJudgementFilePath/$judgementNum/ans/$i.ans
        if [ $? == 0 ]; then
            echo "#&#$i ACCEPTED $(($end - $start))"
        else
            echo "#&#$i WRONG_ANSWER $(($end - $start))"
        fi
    fi
done