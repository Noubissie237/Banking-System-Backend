- mise à jour
```sh
sudo apt-get update ; sudo apt-get upgrade ; sudo apt-get autoremove
```

- désactiver le swapp
```sh
swapoff -a ; mount -a ; vim /etc/fstab
```

- Installer docker
```sh
# Add Docker's official GPG key:
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update
```
```sh
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

- activer le groupe
```sh
sudo usermod -aG docker $USER
```

- Installation du curl et du https
```sh
apt-get update && apt-get install -y apt-transport-https curl
```
- Rajouter le dépot
```sh
echo "deb [signed-by=/etc/apt/keyrings/kubernetes-apt-keyring.gpg] https://pkgs.k8s.io/core:/stable:/v1.28/deb/ /" | sudo tee /etc/apt/sources.list.d/kubernetes.list
```

- Récupérer la clé Gpg
```sh
curl -fsSL https://pkgs.k8s.io/core:/stable:/v1.28/deb/Release.key | sudo gpg --dearmor -o /etc/apt/keyrings/kubernetes-apt-keyring.gpg
```

- Installation 
```sh
sudo apt update ; sudo apt install -y kubelet kubeadm kubectl
```

- initialiser le master
```sh
kubeadm init --apiserver-advertise-address=192.168.56.101 --node-name $HOSTNAME --pod-network-cidr=10.244.0.0/16
```