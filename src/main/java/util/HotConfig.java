package util;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.jmx.ConfigJMXManager;
import com.netflix.config.sources.URLConfigurationSource;
import org.apache.commons.logging.Log;

import java.io.File;
import java.util.logging.LogManager;

public class HotConfig {
    public static void initUseUrl() {
        //配置文件的路径
        File file = new File(System.getProperty("user.dir") + "/config/rp.properties");
        //读取配置调度器延迟初始化时间 默认30s 通过系统参数-Ddynamic.config.initialDelayMillis来设置
        int initialDelayMillis = 30000;
        String initialDelayProperty = System.getProperty("dynamic.config.initialDelayMillis");
        if (initialDelayProperty != null && initialDelayProperty.length() > 0) {
            initialDelayMillis = Integer.parseInt(initialDelayProperty);
        }
        //读取配置调度器延迟时间 默认60s   通过系统参数-Ddynamic.config.delayMills来设置
        int delayMillis = 60000;
        String delayProperty = System.getProperty("dynamic.config.delayMills");
        if (delayProperty != null && delayProperty.length() > 0) {
            delayMillis = Integer.parseInt(delayProperty);
        }

        if (file.exists()) {
            try {
                //url方式配置源  此处用的是本地打包文件rp.properties
                DynamicConfiguration dynamicConfiguration = new DynamicConfiguration(new URLConfigurationSource(file.toURI().toURL()), new FixedDelayPollingScheduler(initialDelayMillis, delayMillis, false));
                if (!ConfigurationManager.isConfigurationInstalled()) {
//                    ConfigurationManager.install(dynamicConfiguration); // source-config
//                    ConfigJMXManager.registerConfigMbean(dynamicConfiguration); //jmx
                }
            } catch (Exception e) {
//                LogManager.exception(e, "init HotConfig error");
            }
        } else {
//            Log.error("init HotConfig error file rp.properties not found");
        }
    }

}
