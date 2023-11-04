/*
 * Copyright (c) 2005, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.util;


/**
 * Error thrown when something goes wrong while loading a service provider.
 *
 * <p> This error will be thrown in the following situations:
 *
 * <ul>
 *
 *   <li> The format of a provider-configuration file violates the <a
 *   href="ServiceLoader.html#format">specification</a>; </li>
 *
 *   <li> An {@link java.io.IOException IOException} occurs while reading a
 *   provider-configuration file; </li>
 *
 *   <li> A concrete provider class named in a provider-configuration file
 *   cannot be found; </li>
 *
 *   <li> A concrete provider class is not a subclass of the service class;
 *   </li>
 *
 *   <li> A concrete provider class cannot be instantiated; or
 *
 *   <li> Some other kind of error occurs. </li>
 *
 * </ul>
 *
 *
 * @author Mark Reinhold
 * @since 1.6
 */

public class ServiceConfigurationError
    extends Error
{

    private static final long serialVersionUID = 74132770414881L;

    /**
     * Constructs a new instance with the specified com.isliujiao.gpt.chatgpt.message.
     *
     * @param  msg  The com.isliujiao.gpt.chatgpt.message, or <tt>null</tt> if there is no com.isliujiao.gpt.chatgpt.message
     *
     */
    public ServiceConfigurationError(String msg) {
        super(msg);
    }

    /**
     * Constructs a new instance with the specified com.isliujiao.gpt.chatgpt.message and cause.
     *
     * @param  msg  The com.isliujiao.gpt.chatgpt.message, or <tt>null</tt> if there is no com.isliujiao.gpt.chatgpt.message
     *
     * @param  cause  The cause, or <tt>null</tt> if the cause is nonexistent
     *                or unknown
     */
    public ServiceConfigurationError(String msg, Throwable cause) {
        super(msg, cause);
    }

}
