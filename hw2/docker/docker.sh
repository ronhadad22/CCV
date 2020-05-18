parser() {
   local prefix=$2
   local s='[[:space:]]*' w='[a-zA-Z0-9_]*' fs=$(echo @|tr @ '\034')
   sed -ne "s|^\($s\)\($w\)$s:$s\"\(.*\)\"$s\$|\1$fs\2$fs\3|p" \
        -e "s|^\($s\)\($w\)$s:$s\(.*\)$s\$|\1$fs\2$fs\3|p"  $1 |
   awk -F$fs '{
      indent = length($1)/2;
      vname[indent] = $2;
      for (i in vname) {if (i > indent) {delete vname[i]}}
      if (length($3) > 0) {
         vn=""; for (i=0; i<indent; i++) {vn=(vn)(vname[i])("_")}
         printf("%s%s%s=\"%s\"\n", "'$prefix'",vn, $2, $3);
      }
   }'
}

#parse yaml file

eval $(parser var.yaml "config_");

#get yaml settings
vm2_ip=$config_variables_VM_2_addr;
A_PORT=$config_variables_A_port;
protocol=$config_variables_protocol
B_duration=$config_variables_B_duration
C_report=$config_variables_C_report
file=$config_variables_MY_FILE

yum install iperf3 

echo "starting ssh session with vm2 on port $A_PORT"

#open ssh session with vm2 and start iperf3 server on it
ssh root@$vm2_ip " docker build --build-arg PORT=$A_PORT -t ronhad/ron_hadad:302788013_203779640 ."


sleep 3;
echo "run container"
ssh root@$vm2_ip "docker run -d --name 302788013_203779640 -p $A_PORT:$A_PORT -t ronhad/ron_hadad:302788013_203779640"



sleep 3;


echo "run container" ;

echo "iperf server online on vm2"


iperf3 -c $vm2_ip -p $A_PORT -t $B_duration -i $C_report 



#print to screen
echo "session ended";


