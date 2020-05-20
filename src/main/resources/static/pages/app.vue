<template>
    <div>
    <v-app>
        <v-toolbar>
            <v-spacer></v-spacer>
            <!--<v-btn v-if="auth" @click="showSignIn" >-->
                <!--SignIn-->
            <!--</v-btn >-->
            <!--<v-btn v-if="exit" @click="showSignIn" >-->
                <!--Exit-->
            <!--</v-btn >-->

        </v-toolbar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                auth:false,
                exit:true,
            }
        },
        // created(){
        //     if (window.location.pathname=="/") {
        //         this.auth = true
        //         this.exit = false
        //     }
        //     if (window.location.pathname=="/login") {
        //         this.exit = false
        //     }
        // },
        // methods: {
        //     showSignIn() {
        //         this.$router.push('/login')
        //         location.reload()
        //     }
        // }

    }
</script>

<style>

</style>