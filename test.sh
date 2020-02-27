#获取参数
while [ -n "$1" ]
do
        case "$1" in
                -l)
                    echo $2
                    shift 2;;
                -r)
                    echo $2
                    shift 2;;
                -b)
                    shift 2;;
                -w)
                    shift 2;;
                --)
                    break ;;
                *)
                    echo "end" ;;
        esac
done