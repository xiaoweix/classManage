package com.classManage.tusdt.base.common;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * description:
 *
 * @author wxcsdb88
 * @since 2017/11/7 10:03
 */
public class RequestDO implements Serializable {
    private static final long serialVersionUID = -5628461623948978501L;

    /**
     * HTTP 请求方法
     */
    private String method;

    /**
     * 查询参数
     */
    private String queryString;

    /**
     * 访问端真实地址
     */
    private String clientAddr;

    /**
     * HTTP 协议
     */
    private String scheme;

    /**
     * the name and version of the protocol the request uses in the form.
     * For example, HTTP/1.1.
     */
    private String protocol;

    /**
     * The returned URL contains a protocol, server name, port number, and server path, but
     * it does not include query string parameters.
     */
    private String requestURL;

    /**
     * Returns the part of this request's URL from the protocol name up to the
     * query string in the first line of the HTTP request. The web container
     * does not decode this String.
     */
    private String requestURI;

    /**
     * The Internet Protocol (IP) port number of the interface on which
     * the request was received.
     */
    private int localPort;

    /**
     * The host name of the Internet Protocol (IP) interface on which
     * the request was received.
     */
    private String localName;

    /**
     * Tthe Internet Protocol (IP) address of the interface on which the
     * request was received.
     */
    private String localAddr;

    /**
     * Returns the host name of the server to which the request was sent. It is
     * the value of the part before ":" in the <code>Host</code> header value,
     * if any, or the resolved server name, or the server IP address.
     */
    private String serverName;

    /**
     * The value of the part after ":" in the <code>Host</code> header value, if any, or the
     * server port where the client connection was accepted on.
     */
    private int serverPort;

    /**
     * The fully qualified name of the client or the last proxy that
     * sent the request. If the engine cannot or chooses not to resolve the
     * hostname (to improve performance), this method returns the dotted-string
     * form of the IP address.
     */
    private String remoteHost;

    /**
     * The Internet Protocol (IP) address of the client or last proxy
     * that sent the request. For HTTP servlets, same as the value of the CGI
     * variable <code>REMOTE_ADDR</code>.
     */
    private String remoteAddr;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getClientAddr() {
        return clientAddr;
    }

    public void setClientAddr(String clientAddr) {
        this.clientAddr = clientAddr;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getLocalAddr() {
        return localAddr;
    }

    public void setLocalAddr(String localAddr) {
        this.localAddr = localAddr;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("method", method)
                .add("queryString", queryString)
                .add("clientAddr", clientAddr)
                .add("scheme", scheme)
                .add("protocol", protocol)
                .add("requestURL", requestURL)
                .add("requestURI", requestURI)
                .add("localPort", localPort)
                .add("localName", localName)
                .add("localAddr", localAddr)
                .add("serverName", serverName)
                .add("serverPort", serverPort)
                .add("remoteHost", remoteHost)
                .add("remoteAddr", remoteAddr)
                .toString();
    }
}
