# Etape du déploiement avec kubernetes (Version 1)

- Mettre à jour les packages
```sh
sudo apt-get update
sudo apt-get install -y apt-transport-https ca-certificates curl
```

- Installer Docker
```sh
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
echo "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list
sudo apt-get update
sudo apt-get install -y docker-ce
```


- Installer kubernetes (avec adm)
```sh
sudo apt-get install -y apt-transport-https ca-certificates curl
curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -
echo "deb https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list
sudo apt-get update
sudo snap install kubectl --classic
sudo snap install kubelet --classic
sudo snap install kubeadm --classic
sudo apt-mark hold kubelet kubeadm kubectl
```

- Installer kubeadm 
```sh
sudo apt-get install -y kubelet kubeadm kubectl
```

- Initialisation du cluster
```sh
sudo kubeadm init --pod-network-cidr=192.168.0.0/16
```





# Etape du déploiement avec kubernetes (Version 2)
- Mettre la machine à jour
```sh
sudo apt-get update ; sudo apt-get upgrade
```

- Désactivation du swap
```sh
sudo swapoff -a
sudo sed -i '/ swap / s/^\(.*\)$/#\1/g' /etc/fstab
```

- Installation des oubils
```sh
sudo apt install critcl
sudo apt install conntrack
sudo apt install ethtool
```