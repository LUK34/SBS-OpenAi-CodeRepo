# Pre-requisites

## What are the different types of prompts in Prompt Engineering ??
- In prompt engineering, prompts are carefully crafted inputs used to guide the output of AI language models like ChatGPT or other LLMs. 
- Different types of prompts serve different goals based on the context, task, and expected response. 
- Here's a breakdown of the different types of prompts commonly used in prompt engineering.

- **1. Instruction-based Prompts: **
- **Purpose:** Tell the model exactly what to do.
- **Example:** "Summarize the following paragraph in one sentence."

- **2. Input-Output Prompts: **
- **Purpose:** Provide examples of inputs and expected outputs.
- **Example:**
- Q: What is the capital of France?  
- A: Paris  
- Q: What is the capital of Japan?  
- A:

- **3. Few-shot Prompts: **
- **Purpose:** Show the model how to perform a task with a few examples.
- **Example:**
- Translate English to French:  
- English: Hello  
- French: Bonjour  
- English: Thank you  
- French:

- **4. Zero-shot Prompts: **
- **Purpose:** Ask the model to do something without showing any examples.
- **Example:**
- "Translate 'Good evening' to Spanish."

- **5. Chain-of-Thought (CoT) Prompts: **
- **Purpose:** Encourage the model to show step-by-step reasoning.
- **Example: **
- "If you have 3 apples and buy 2 more, how many do you have? Think step by step."

- **6. Role-based Prompts: **
- **Purpose:** Assign the model a specific role or persona
- **Example:**
- "You are an experienced Java developer. Explain what a design pattern is to a beginner."

- **7. Refinement Prompts: **
- **Purpose:** Iterate on or improve a previous response.
- **Example:**
- "Can you make the above explanation more concise?"

- **8. Multi-turn (Conversational) Prompts: **
- **Purpose:** Maintain context across multiple interactions.
- **Example:**
- User: What's the best way to study AI?
- AI: Start with the basics of ML and Python...
- User: Can you suggest a beginner-friendly course?

- **9. Hypothetical / Scenario-based Prompts: **
- **Purpose:** Create or explore fictional or speculative scenarios.
- **Example:** 
- "Imagine you're a detective in the year 2050. How would you solve a cybercrime?"

- **10. Function Calling / Tool Use Prompts (for advanced models like GPT-4)**
- **Purpose:** Structure prompts to invoke functions or APIs.
- **Example:**
- "Call the weather function with location='Paris' and date='2025-04-01'"

## What is RAG ??
- RAG in Spring AI refers to Retrieval-Augmented Generation, 
- a pattern that combines large language models (LLMs) with external data sources
-  to improve the accuracy and relevance of responses. 
- This is especially useful when you want your LLM to answer questions based on your own documents or data,
-  not just its pre-trained knowledge.

## How RAG works in Spring AI ??
- In Spring AI, RAG typically involves three main components:
- **Retrieval (R):**
- Pull relevant documents from a vector store (e.g., Pinecone, Redis, Elasticsearch) based on a user’s query.
- The documents are usually embedded using an embedding model (e.g., OpenAI, Hugging Face).
- **Augmentation (A):**
- Add (augment) the retrieved documents into the prompt that will be sent to the LLM.
- **Generation (G):**
- The LLM uses both the original user query and the retrieved documents to generate a final, context-rich response.

## Example Use Case in Spring AI:
- Let’s say you have a knowledge base (PDFs, docs, etc.) and want users to ask questions like “What is the refund policy?”
- Spring AI with RAG will:
- 1. Convert the documents into vector embeddings and store them.
- 2. On query, retrieve relevant chunks of text.
- 3. Inject those chunks into the prompt.
- 4. Send the prompt to GPT (or other LLM).
- 5. Return an accurate, document-based answer.

## How to implement api keys in run configuration without embedding them in application.properties file??
- **Select Project -> Run as -> Run configuration -> Java Application -> Starter -> Enviroment -> Set the Enviroment variable -> Key, Value pair**
- OPENAI_API_KEY -> enter the value in value field.
- OPENAI_PROJECT_ID -> enter the value in value field.

## What are the different OutputConverters in Spring AI??
- In Spring AI, the package org.springframework.ai.converter provides several output converters 
- designed to transform the raw outputs from AI models into structured Java objects. 
- These converters facilitate the handling of AI responses by converting them into formats like Java Beans, Maps, 
- or Lists. The primary converters include

- **1.BeanOutputConverter<T>**
- **Description:** 
- Converts AI model outputs into instances of a specified Java class (POJO).
- **Usage:** 
- Ideal for mapping structured AI responses directly to Java objects.

- **2.MapOutputConverter**
- **Description:** 
- Transforms AI model outputs into a Map<String, Object>.
- **Usage:** 
- Useful when the AI response is a JSON object that can be represented as a map.

- **3.ListOutputConverter**
- **Description:**
- Converts AI model outputs into a List<String>.​
- **Usage:**
- Suitable for responses that are arrays or lists.



- .withN(imgN)	
- Number of images to generate (e.g., 1, 2)

- .withWidth(imgWidth)	
- Width of the generated image (e.g., 512, 1024)

- .withHeight(imgHeight)	
- Height of the generated image

- .withQuality(imgQuality)
- Quality setting (hd or standard) depending on the model capabilities
