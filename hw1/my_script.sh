#!/bin/sh

#path to yaml parsing function
. /home/b/bb/bbb/parser.sh

#parse yaml file
eval $(parser var.yml "config_");

#get yaml settings
vm2_ip=$config_variables_VM_2_addr;
A_port=$config_variables_A_port;
protocol=$config_variables_protocol
B_duration=$config_variables_B_duration
C_report=$config_variables_C_report
file=$config_variables_MY_FILE

echo "starting ssh session ron with vm2ff"
#open ssh session with vm2 and start iperf3 server on it
ssh root@$vm2_ip "iperf3 -s -p $A_port -1 >> $file ; exit;" &
sleep 2;

echo "iperf server online on vm2"

if [ "$protocol" = "TCP" ];  then
    iperf3 -c $vm2_ip -p $A_port -t $B_duration --i $C_report > $file &
  else
    iperf3 -c $vm2_ip -u -p $A_port -t $B_duration --i $C_report > $file & 
fi


#print to screen
sleep 2;
sleep $B_duration;
cat $file
echo "session ended";
