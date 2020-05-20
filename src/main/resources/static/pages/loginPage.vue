<template>
    <v-card class="ma-auto" width="40%" >
        <v-tabs>
            <v-tab >Sign In</v-tab>
            <v-tab-item>
                <form>
                    <v-layout row class="mx-0">
                        <v-text-field
                                v-model="logUser"
                                label= "Your login"
                        >
                        </v-text-field>
                    </v-layout>
                    <v-layout row class="mx-0">
                        <v-text-field
                                v-model="passUser"
                                type="password"
                                label="Your password"
                        >
                        </v-text-field>
                    </v-layout>
                    <br>
                    <v-btn v-if="logUser!=''&&passUser!=''&&picked!=''" @click="signin">Sign In</v-btn>
                </form>
            </v-tab-item>

        </v-tabs>
    </v-card>
</template>
<!--<script>-->
    <!--import user from 'api/worker.js'-->

    <!--export default {-->
        <!--data(){-->
            <!--return {-->
                <!--name: "loginPage",-->
                <!--logUser: '',-->
                <!--passUser: ''-->
            <!--}-->
        <!--},-->
        <!--methods: {-->
            <!--signin() {-->

                <!--user.add({-->
                    <!--userName: this.logUser,-->
                    <!--passUser: this.passUser,-->
                    <!--check: '0'-->
                <!--},).then(response => {-->
                    <!--if (response.data == "") {-->
                        <!--alert("Wrong date!")-->
                    <!--} else {-->
                        <!--localStorage.setItem("userId",response.data.userId)-->
                        <!--localStorage.setItem("userName",response.data.userName)-->
                        <!--this.$router.push('/workers')-->
                        <!--location.reload()-->
                    <!--}-->
                <!--})-->

            <!--}-->
        <!--}-->

    <!--}-->
<!--</script>-->

<style>
</style>