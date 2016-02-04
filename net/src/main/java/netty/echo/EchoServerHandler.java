/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package netty.echo;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * Handler implementation for the echo server.
 */
public class EchoServerHandler extends SimpleChannelUpstreamHandler {

    private static final Logger logger = Logger.getLogger(
            EchoServerHandler.class.getName());

    private final AtomicLong transferredBytes = new AtomicLong();

    public long getTransferredBytes() {
        return transferredBytes.get();
    }

    @Override
    public void messageReceived(
            ChannelHandlerContext ctx, MessageEvent e) {
        // Send back the received message to the remote peer.
    	/**
    	 * 没有decoder的清空下，e.getMessage()的类型为org.jboss.netty.buffer.ChannelBuffer
    	 */
    	long readBytes = ((ChannelBuffer) e.getMessage()).readableBytes();
    	byte[] bytes = ((ChannelBuffer) e.getMessage()).array();
    	
    	System.out.println("msg type:" + e.getMessage().getClass());
    	System.out.println("read bytes:" + readBytes);
    	
    	for(byte b : bytes) {
    		System.out.printf("%02x ", b);
    	}
    	System.out.println();
    	
        transferredBytes.addAndGet(readBytes);
        
        /**
         * 如果没有配置encoder，这里就只能写入org.jboss.netty.buffer.ChannelBuffer
         */
        e.getChannel().write(e.getMessage());
        
        System.out.println("total bytes received:" + transferredBytes);
        System.out.println("------------------------------------");
    }

    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e) {
        // Close the connection when an exception is raised.
        logger.log(
                Level.WARNING,
                "Unexpected exception from downstream.",
                e.getCause());
        e.getChannel().close();
    }
}
