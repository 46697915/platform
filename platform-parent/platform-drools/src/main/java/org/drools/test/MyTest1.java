package org.drools.test;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTest1 {
    public static final Logger L = LoggerFactory.getLogger(MyTest1.class);

    public static final void main(final String[] args) {
        // KieServices is the factory for all KIE services
        KieServices ks = KieServices.Factory.get();

        // From the kie services, a container is created from the classpath
        KieContainer kc = ks.getKieClasspathContainer();

        KieSession ksession = kc.newKieSession("MyTest1");

        ksession.addEventListener(new DebugAgendaEventListener());
        ksession.addEventListener(new DebugRuleRuntimeEventListener());

        Person zhangsan = new Person("张三", (byte) 30);
        ksession.insert(zhangsan);

        Person xiaosan = new Person("小三", (byte) 18);
        ksession.insert(xiaosan);

        // and fire the rules
        int total = ksession.fireAllRules();
        ksession.dispose();

        L.info("执行了 {} 条规则", total);
        L.info("执行后的姓名:{}", zhangsan.getName());
        L.info("执行后的姓名:{}", xiaosan.getName());
    }

    public static class Person {
        private String name;
        private byte age;

        public Person() {
        }

        public Person(String name, byte age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public byte getAge() {
            return age;
        }

        public void setAge(byte age) {
            this.age = age;
        }
    }
}
