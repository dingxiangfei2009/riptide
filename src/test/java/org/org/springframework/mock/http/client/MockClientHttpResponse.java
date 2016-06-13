/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.mock.http.client;

/*
 * ⁣​
 * Riptide
 * ⁣⁣
 * Copyright (C) 2015 - 2016 Zalando SE
 * ⁣⁣
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ​⁣
 */

import java.io.IOException;
import java.io.InputStream;

import com.google.gag.annotation.remark.Hack;
import com.google.gag.annotation.remark.OhNoYouDidnt;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.util.Assert;

/**
 * Mock implementation of {@link ClientHttpResponse}.
 *
 * @author Rossen Stoyanchev
 * @since 3.2
 */
@Hack
@OhNoYouDidnt
public class MockClientHttpResponse extends MockHttpInputMessage implements ClientHttpResponse {

    private final HttpStatus status;


    /**
     * Constructor with response body as a byte array.
     */
    public MockClientHttpResponse(byte[] body, HttpStatus statusCode) {
        super(body);
        Assert.notNull(statusCode, "statisCode is required");
        this.status = statusCode;
    }

    /**
     * Constructor with response body as InputStream.
     */
    public MockClientHttpResponse(InputStream body, HttpStatus statusCode) {
        super(body);
        Assert.notNull(statusCode, "statisCode is required");
        this.status = statusCode;
    }

    @Override
    public HttpStatus getStatusCode() throws IOException {
        return this.status;
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return this.status.value();
    }

    @Override
    public String getStatusText() throws IOException {
        return this.status.getReasonPhrase();
    }

    @Override
    public void close() {
        try {
            if (this.getBody() != null) {
                this.getBody().close();
            }
        } catch (IOException e) {

        }
    }

}
