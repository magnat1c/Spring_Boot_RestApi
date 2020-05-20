<template>
    <div>
        <div class="mb-1">
            <div>
                <p class="title text&#45;&#45;mb-1">
                    Welcome, {{userName}}!
                    <v-btn
                            @click="sheet = !sheet"
                    >
                        Add
                    </v-btn>
                    <v-btn
                            @click="sheet1 = !sheet1"
                    >
                        Delete
                    </v-btn>
                    <v-bottom-sheet v-model="sheet">
                        <v-sheet>
                            <v-btn
                                    class="mt-6"
                                    text
                                    color="red"
                                    @click="sheet = !sheet"
                            >close</v-btn>
                            <form>
                                <v-text-field
                                        v-model="productForm.NameProduct"
                                        label="Name"
                                >
                                </v-text-field>
                                <v-text-field
                                        v-model="productForm.Size"
                                        label="Size"
                                >
                                </v-text-field>
                                <v-text-field
                                        v-model="productForm.Description"
                                        label="Description"
                                >
                                </v-text-field>
                                <v-text-field
                                        v-model="productForm.Price"
                                        label="Price "
                                >
                                </v-text-field>
                                <v-btn v-if ="productForm.NameProduct!=''&&productForm.Size!=''
                                &&productForm.Description!=''&&
                        productForm.Price!=''" @click="addProduct(productForm)"
                                >
                                    Add</v-btn>
                            </form>
                        </v-sheet>
                    </v-bottom-sheet>
                    <v-bottom-sheet v-model="sheet1">
                        <v-sheet>
                            <v-btn class="mt-6"
                                   text
                                   color="red"
                                   @click="sheet1 = !sheet1">
                                close
                            </v-btn>
                            <div v-for="prod in productMas">
                                <v-card>
                                    <v-list class="align-self-start">
                                  <span class="light-black--text text--darken-3">
                                     Product: {{prod.productName}}
                                      <v-btn @click="deleteProduct(prod)">
                                            delete
                                        </v-btn>
                                  </span>

                                    </v-list>
                                </v-card>
                            </div>
                        </v-sheet>
                    </v-bottom-sheet>
                </p>
            </div>
        </div>
        <!--<div v-for="deal in deals">-->
            <!--<v-container class="mt-5">-->
                <!--<v-card-->
                        <!--class="mx-auto"-->
                        <!--outlined-->
                <!--&gt;-->
                    <!--<v-list three-line>-->
                        <!--<v-list class="align-self-start">-->
                            <!--<v-list-->
                                    <!--class="headline mb-1"-->
                            <!--&gt;-->
                                  <!--<span class="light-black&#45;&#45;text text&#45;&#45;darken-3">-->
                                     <!--Product: {{deal.product.productName}}   |   Client: {{deal.client.name}}-->
                                  <!--</span>-->
                            <!--</v-list>-->
                            <!--<p class="title text&#45;&#45;primary mb-7">-->
                                <!--Start price: {{deal.product.price}} ₽-->
                            <!--</p>-->
                            <!--<p>-->
                                <!--Finish price: {{deal.priceClient}} ₽-->
                            <!--</p>-->
                        <!--</v-list>-->
                    <!--</v-list>-->

                    <!--<v-card-actions>-->
                        <!--<v-btn  color="red"  @click="cancel(deal)">-->
                            <!--Cancel-->
                        <!--</v-btn>-->
                        <!--<v-btn  color="light-green" dark @click="sell(deal)">-->
                            <!--Sell-->
                        <!--</v-btn>-->
                    <!--</v-card-actions>-->
                <!--</v-card>-->
            <!--</v-container>-->
        <!--</div>-->
    </div>
</template>

<script>

    // import deal from 'api/deal.js'
    // import message from 'api/message.js'
    import product from "../api/product";

    export default {
        name: "User",
        data(){
            return {
                sheet: false,
                sheet1: false,
                //deals:[],
                //messages:[],
                //mes:[],
                productForm:{NameProduct:'',Size:'', Description:'',
                    Price:''},
                sellerNames: localStorage.getItem("userName"),
                sellerId: localStorage.getItem("userId"),
                productMas:[],
            }
        },
        created(){
            product.get().then(result=>{
                result.data.forEach(data=>{
                    if (data.fk_seller==this.sellerId){
                        this.productMas.push(data)
                    }
                })
            })

            // deal.get().then(result=> {
            //     result.data.forEach(data => {
            //         if (data.res=="null"&& this.productMas.length!=0) {
            //             this.deals.push(data)
            //         }
            //     })
            // })
        },
        methods:{
            // sell(deals){
            //     message.add({idDeal:deals.dealId, text: "true"})
            //     deal.update({dealId:deals.dealId,  date: deals.date,
            //         fk_id_client: deals.client.clientId,
            //         fk_id_product: deals.product.productId,priceClient:deals.priceClient, res:'1'}).then(location.reload())
            // },
            // cancel(deals) {
            //     message.add({idDeal:deals.dealId, text: "false"})
            //     deal.update({dealId:deals.dealId,  date: deals.date,
            //         fk_id_client: deals.client.clientId,
            //         fk_id_product: deals.product.productId,priceClient:deals.priceClient, res:'1'}).then(location.reload())
            // },
            addProduct(productForm){
                if ((productForm.Price.match(/[a-z]/i)==null) &&(productForm.Size.match(/[a-z]/i)==null) ) {
                    product.add({
                        productName: productForm.NameProduct,
                        size: productForm.Size,
                        description: productForm.Description,
                        price: productForm.Price, fk_seller: this.sellerId
                    }).then(window.location.reload())
                } else {
                    alert("Error! Wrong data")
                }
            },
            // deleteProduct(prod){
            //     product.remove(prod.productId).then(window.location.reload())
            // }

        }
    }
</script>

<style scoped>

</style>