
<script>

//

</script>
    <input type="hidden" id="productSelectKey" name="productSelectKey" value="${productVO.idx}">
    <input type="hidden" id="productSelectName" name="productSelectName" value="${productVO.productName}">
    <div class="row">
          <div class="col-lg-12">
              <div class="ibox float-e-margins">
                  <div class="ibox-content">
                      <div class="table-responsive">
                          <table class="table table-striped">
						  <thead></thead>
						  <tbody>
						    <tr>
					          <td style="background-color:#F5F5F5">상품분류</td>
					          <td></td>
						    </tr>
						    <tr>
					          <td style="background-color:#F5F5F5">상품명</td>
					          <td>${productVO.productName}</td>
						    </tr>
						    <tr>
					          <td style="background-color:#F5F5F5">상품코드</td>
					          <td>${productVO.productCode}</td>
						    </tr>
						    <tr>
					          <td style="background-color:#F5F5F5">모델명</td>
					          <td>${productVO.modelName}</td>
						    </tr>
						    <tr>
					          <td style="background-color:#F5F5F5">제조사</td>
					          <td>${productVO.makeCompany}</td>
						    </tr>
						    <tr>
					          <td style="background-color:#F5F5F5">소비자가</td>
					          <td>${productVO.salesPrice}</td>
						    </tr>
						  </tbody>
					    </table>
 					</div>
               </div>
           	</div>
		</div>
	</div>