package com.sireikan.gack.http.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class IndexController {

    @Autowired
    lateinit var redisTemplate: StringRedisTemplate

    @GetMapping("/")
    fun index(model: Model): String {
        return "index"
    }

    @GetMapping("/redis/{key}")
    fun getRedis(@PathVariable("key") key: String, model: Model): String {
        val stringOperation: ValueOperations<String, String> = redisTemplate.opsForValue()
        val value: String? = stringOperation.get(key)
        if (value != null) {
            model.addAttribute("value", value)
        } else {
            model.addAttribute("value", "なし")
        }
        return "redis"
    }

    @GetMapping("/redis/{key}/{value}")
    fun registerRedis(@PathVariable("key") key: String, @PathVariable("value") value: String, model: Model): String {
        val stringOperation: ValueOperations<String, String> = redisTemplate.opsForValue()
        stringOperation.set(key, value)
        model.addAttribute("key", key)
        model.addAttribute("value", value)
        return "redis"
    }
}
