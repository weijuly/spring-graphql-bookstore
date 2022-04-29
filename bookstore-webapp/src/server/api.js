const express = require('express')


const router = express.Router()

router.get('/', (req, res) => {
    res.send({
        application: "bookstore"
    })
})

module.exports = router