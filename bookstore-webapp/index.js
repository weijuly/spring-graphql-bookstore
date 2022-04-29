const express = require('express')
const morgan = require('morgan')
const api = require('./src/server/api')

const PORT = 8000
const app = express()

app.use('/', morgan('combined'))
app.use('/__api', api)
app.use('/', express.static('public'))

app.listen(PORT, () => {
    console.log(`BookStoreApp listening on ${PORT}`)
})
