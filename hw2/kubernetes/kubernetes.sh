
docker pull ronhad/ron_hadad:im_ver1_302788013_203779640
docker pull ronhad/ron_hadad:im_ver2_302788013_203779640
kubectl create -f hw2_resources.yaml

sleep 7
SER_IP=$(kubectl describe svc hw2-service  |  grep IP: | cut -d" " -f17-)

end=$((SECONDS+240))
while [ $SECONDS -lt $end ]; do  wget $SER_IP:30555 -q -O-; done    


# roll to version 2 
echo "start rolling to version 2"
kubectl set image deployment.apps/hw2-deploy hw2-deploy=ronhad/ron_hadad:im_ver2_302788013_203779640 --record=true
kubectl rollout status deployment/hw2-deploy
wget $SER_IP:30555 -q -O-

# roll to version 1
echo "start rolling back to version 2"
kubectl rollout undo deployment.apps/hw2-deploy
kubectl rollout status deployment/hw2-deploy 
wget $SER_IP:30555 -q -O-


# delete all 
kubectl delete service/hw2-service 
kubectl delete deployment.apps/hw2-deploy 
kubectl delete hpa/hw2-autoscaling 

echo 'Great Succesds'

