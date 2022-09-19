var express = require("express");
var router = express.Router();
var usuarioController = require("../controllers/usuarioController");

router.get("", function (req, res) {
    usuarioController.listar(req, res);
});

router.post("", function (req, res) {
    usuarioController.cadastrar(req, res);
});

router.post("/autenticacao", function (req, res) {
    usuarioController.logar(req, res);
});

module.exports = router;