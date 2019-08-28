package zzc.starter;

public class HelloService {

    private HelloProperties helloProperties;

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String sayHelloZzc(String name) {
        return helloProperties.getPrefix() + "-" + name + "-" + helloProperties.getSuffix();
    }
}
