package com.weesftw.client;

import com.weesftw.MainConfig;

public final class LLMClientFactory {
    
    public static final LLMClient INSTANCE;
    
    static {
        INSTANCE = switch (MainConfig.INSTANCE.getLlModel()) {
            case OPENAI -> new OpenAIClient();
        };
    }
}
