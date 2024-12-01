# -*- modu: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
    config.vm.define "masternode" do |kub|
        kub.vm.box = "bento/ubuntu-22.04"
        kub.vm.hostname = "masternode"
        kub.vm.box_url = "bento/ubuntu-22.04"

        kub.vm.network :private_network, ip: "192.168.56.101"

        kub.vm.provider :virtualbox do |v|
            v.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
            v.customize ["modifyvm", :id, "--memory", 2048]
            v.customize ["modifyvm", :id, "--name", "masternode"]
            v.customize ["modifyvm", :id, "--cpus", "2"]
        end
    end


    config.vm.define "workernode-nk" do |kub|
        kub.vm.box = "bento/ubuntu-22.04"
        kub.vm.hostname = "workernode-nk"
        kub.vm.box_url = "bento/ubuntu-22.04"

        kub.vm.network :private_network, ip: "192.168.56.102"

        kub.vm.provider :virtualbox do |v|
            v.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
            v.customize ["modifyvm", :id, "--memory", 2048]
            v.customize ["modifyvm", :id, "--name", "workernode-nk"]
            v.customize ["modifyvm", :id, "--cpus", "2"]
        end
    end
end
