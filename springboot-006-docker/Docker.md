# 1､简介

**Docker**是一个开源的应用容器引擎

Docker支持将软件编译成一个镜像，然后在镜像中对各保软件做好配置，将镜像发布出去，其他使用者可以直接使用这个镜像

运行中的这个镜像称为容器，容器启动是非常快速的

# 2､Docker核心概念

docker镜像(Images)：Docker镜像是用于创建Docker容器的模板

docker容器(Container)：容器是独立运行的一个或一组应用

docker客户端(Client)：客户端通过命令行或者其他工具使用Docker API()与Docker的守护进程通信

docker主机(Host)：一个物理或者虚拟的机器用于执行Docker守护进程和容器

docker仓库(Registry)：Docker仓库用来保存镜像，可以理解为代码控制中的代码仓库。Docker Hub(https://hub.docker.com)提供了庞大的镜像集合供使用

使用Docker步骤：

​	1､安装Docker

​	2､去Docker仓库找到这个软件对应的镜像

​	3､使用Docker运行这个镜像，这个镜像就会生成一个Docker容器

​	4､对容器的启动停止就是对软件的启动停止