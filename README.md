# EasyAI

## 项目介绍

**EasyAI** 是一个基于 **Spring Cloud** 的微服务架构项目，专注于提供轻量级的 AI 模型交互能力。本项目基于RuoYi开源框架进行构建，集成了 LangChain4j 框架，为开发者提供快速构建 AI 应用的解决方案，支持多种大语言模型的接入和调用。

## 核心特性

- 🚀 **微服务架构**: 基于Spring Cloud构建，实现模块化设计
- 🤖 **AI框架集成**: 
  - 集成 LangChain4j，支持智能对话、文档处理等多种AI能力
  - 支持 OpenAI GPT、Anthropic Claude 等多种模型接入
- 🔐 **安全可靠**: 集成OAuth2/JWT认证，确保系统安全
- ⚡ **高性能**: 支持负载均衡和服务容错
- 🔍 **服务治理**: 集成Nacos实现服务注册与发现
- 📊 **可观测性**: 分布式链路追踪，实时监控系统状态

## 技术栈

- **基础框架**: 
  - Spring Boot 3.x
  - Spring Cloud
  - LangChain4j
- **AI模型支持**:
  - OpenAI GPT
  - Anthropic Claude
  - 更多模型持续集成中...
- **服务注册**: Nacos/Eureka
- **网关服务**: Spring Cloud Gateway
- **认证授权**: Spring Security + JWT/OAuth2
- **负载均衡**: Spring Cloud LoadBalancer
- **服务容错**: Resilience4j
- **链路追踪**: Spring Cloud Sleuth + Zipkin
- **缓存中间件**: Redis

## 系统要求

- JDK 21+
- Maven 3.8+
- Redis
- Nacos/Eureka
- Docker (可选)
- OpenAI API Key 或其他AI模型访问凭证

## 快速开始

### 1. 获取代码

```bash
git clone https://github.com/OkayYang/EasyAI.git
cd EasyAI
```

### 2. 环境配置

修改各服务的配置文件：

```yaml
# application.yml 示例
spring:
  application:
    name: easyai-service
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848

# AI模型配置
langchain4j:
  chat-model:
    provider: openai
    api-key: ${OPENAI_API_KEY}
    model-name: gpt-3.5-turbo
    temperature: 0.7
    timeout: 60s
```

### 3. 启动服务

方式一：手动启动各个服务

```bash
# 启动网关服务
cd api-gateway && mvn spring-boot:run

# 启动AI服务
cd ai-service && mvn spring-boot:run

# 启动用户服务
cd user-service && mvn spring-boot:run
```

方式二：Docker部署

```bash
docker-compose up -d
```

## API示例

### AI对话接口

```bash
# 发送对话请求
curl -X POST http://localhost:8080/api/ai/chat \
     -H "Authorization: Bearer YOUR_TOKEN" \
     -H "Content-Type: application/json" \
     -d '{
           "message": "你好，请介绍下你自己",
           "model": "gpt-3.5-turbo",
           "options": {
             "temperature": 0.7,
             "maxTokens": 500
           }
         }'
```


## 项目结构

```
EasyAI
├── api-gateway                 # API网关服务
├── auth-service               # 认证授权服务
├── ai-service                # AI服务（集成LangChain4j）
├── user-service              # 用户管理服务
├── common                    # 公共模块
│   ├── common-core           # 核心功能
│   ├── common-ai             # AI相关组件
│   └── common-security       # 安全组件
└── docker-compose.yml        # Docker编排文件
```

## 开发计划

- [ ] 支持更多AI模型接入（文心一言、讯飞星火等）
- [ ] 增加向量数据库集成
- [ ] 完善文档处理能力
- [ ] 添加对话记忆功能
- [ ] 提供完整的API文档
- [ ] 优化系统性能

## 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 提交Pull Request

## 开源协议

本项目采用 [MIT](LICENSE) 协议开源。

## 联系我们

- 项目地址：https://github.com/OkayYang/EasyAI
- 问题反馈：https://github.com/OkayYang/EasyAI/issues

---

如果觉得这个项目对你有帮助，欢迎给我们一个 Star ⭐️



