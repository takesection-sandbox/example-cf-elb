FROM amazonlinux

RUN yum install -y yum-utils && \
  yum-config-manager --add-repo "https://rpm.releases.hashicorp.com/AmazonLinux/hashicorp.repo" && \
  yum -y install packer && \
  amazon-linux-extras install -y epel && \
  yum install -y ansible openssh-clients && \
  yum install pip groff -y && \
  pip install pip --upgrade && \
  pip install awscli
