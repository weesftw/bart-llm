package com.weesftw.client;

import com.weesftw.model.LLMData;

public interface LLMClient<T extends LLMData> {

    T proceed(String content);
}
