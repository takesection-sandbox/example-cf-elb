ビルド手順
=====

```
$ docker run -v `pwd`:/work -v m2:/root/.m2 -it --rm maven /bin/bash
# cd /work
# bash build.sh
# mvn clean install
# exit
```

```
$ docker run -v /var/run/docker.sock:/var/run/docker.sock -v `pwd`:/work -v m2:/root/.m2 -it --rm gradle /bin/bash
# cd /work/example
# gradle bootBuildImage
# exit
```

# ローカルPC での動作確認

```
$ docker run -p 8080:8080 -it --rm example:0.1.0-SNAPSHOT
```

http://localhost:8080/test

# AWS Fargate での動作確認

まず、ネットワーク (VPC、Internet Gateway、ルートテーブル、ECR などを構成)

```
$ aws cloudformation create-stack --region 'ap-northeast-3' --stack-name 'network' --template-body "`cat aws/network.yml`"
```

ビルドしたイメージを ECR に登録

```
export AWS_ACCOUNT_ID=<YOUR AWS ACCOUNT ID>
aws ecr get-login-password --region ap-northeast-3 | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.ap-northeast-3.amazonaws.com
docker tag example:0.1.0-SNAPSHOT ${AWS_ACCOUNT_ID}.dkr.ecr.ap-northeast-3.amazonaws.com/example:0.1.0-SNAPSHOT
docker push ${AWS_ACCOUNT_ID}.dkr.ecr.ap-northeast-3.amazonaws.com/example:0.1.0-SNAPSHOT
```

```
$ aws cloudformation create-stack --region 'ap-northeast-3' --stack-name 'deploy' --template-body "`cat aws/deploy.yml`" --capabilities CAPABILITY_IAM
```
