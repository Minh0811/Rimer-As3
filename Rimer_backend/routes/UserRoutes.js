const express = require('express');
const router = express.Router()
const Users = require('../model/UserModel');
const { OAuth2Client } = require('google-auth-library')

const clientId = "938609500238-he02rg0ue7annqid2ttnmlf4eh30bbmm.apps.googleusercontent.com";

const authClient = new OAuth2Client(clientId)

router.post("/", (req, res) => {
    const { idToken } = req.body;
    if (idToken) {
        authClient.verifyIdToken({ idToken, audience: clientId })
            .then(async response => {
                // console.log(response)
                const { email_verified, email, name, picture } = response.payload
                if (email_verified) {
                    const user = await Users.findOne({ email });
                        if(user){
                            return res.json({
                                message: email,
                                picture: user.picture,
                                name: user.name,
                                email: user.email
                              });
                        }
                        else{
                            let pass = email + clientId
                            const newUser = new Users({
                                name,
                                email,
                                password: pass,
                                picture,
                            });

                            newUser.save()
                            
                            res.status(200).json({
                                name: newUser.name,
                                email: newUser.email,
                                picture: newUser.picture,
                            });
                        }
                }
            })
            .catch(err => { console.log(err) })
    }
})

module.exports = router;