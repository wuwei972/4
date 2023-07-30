import request from '@/utils/request.ts';export function test() { return request({   url: '/test/testGet/',   method: 'get',   params: {   } });}export function testPost(body) { return request({   url: '/test/testPost/',   method: 'post',   data: {     body: body,   } });}export function testGetById(id) { return request({   url: '/test/testGet/'+id+'/',   method: 'get',    });}export function testNotRestful(id,name) { return request({   url: '/test/testNotRestful/',   method: 'get',   params: {     id: id,     name: name,   } });}