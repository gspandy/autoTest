# autoTest
接口自动化测试平台代码重构
### 第一版效果图https://wenku.baidu.com/view/f2cc5004ba68a98271fe910ef12d2af90242a86e

1．	接口自动化
接口自动化包含接口管理、报文管理、测试集管理、测试执行、测试报告几个模块，用户通过创建接口->创建接口下报文->创建报文场景->配置场景数据->建立测试集->执行测试来完成接口自动化整个测试管理流程，并通过测试报告模块来查看测试结果详情。
 
1.1．	接口管理
接口管理主要用于接口的创建、修改、删除，并且可以管理接口下的所有入参节点。
 
1.1.1.	接口添加
通过点击”添加接口”按钮来打开接口添加窗口：
 

输入完整接口信息，点击提交即可保存。

1.1.2.	入参管理
点击接口列表页面的入参管理按钮打开对应接口的入参管理页面：
 

接口入参可单条添加，同时也能通过导入入参报文的json串来批量添加：
 
 

1.2．	报文管理
报文管理主要对接口下属各种不同格式类型的报文进行管理，同时也能管理对应报文的各种测试场景、配置测试场景的测试数据、返回报文的参数验证规则编辑以及执行单次测试等。
 
1.2.1.添加报文
	通过点击添加报文按钮来打开添加窗口：
	 
	
	选择报文对应的接口，输入报文名称（类似如”根据userid查询用户基本信息”或者”根据custid查询用户基本信息”），填入该报文对应的请求入参json，点击验证按钮，系统将会判断该json报文中包含的入参节点是否都存在与对应接口的参数库中，验证成功即可提交保存。
 
1.2.2.测试场景
测试场景是根据报文下入参的不同状态或者不同类型的数据来细分出来的，例如”正常用户根据id查询用户信息”和”欠费用户根据id查询用户信息”。
 

添加场景只需要输入场景名称和备注即可。
 

 
对应每个测试场景可进行上图中的操作，从左到右分别表示：验证规则编辑、场景测试、测试数据编辑、删除等操作。具体详见下方说明：
1.2.2.1测试数据
 



添加场景的测试数据需要输入数据的标示，该标示应该可以简明直接的表示各数据间的差异，例如场景对应的报文名为”根据userid查询用户信息”，那么该场景的数据标示应该填入对应的userid。
 

	受理类接口下的场景数据在使用完一次之后就会打上已使用标志，无法再次使用；查询类接口下的场景数据可以无限次使用，使用标志不会被更改。
	 
1.2.2.2.验证规则
验证规则主要用于验证返回报文的正确性，分为：全局验证、全文验证、节点验证。
默认为全局验证。

	 
全局验证：该规则在测试设置中统一设置，对返回报文进行解析，判断报文中有无ReturnCode节点，并取出该值同预设置的返回值进行比对，判断返回是否正确。
 
全文验证：对返回报文的全文进行匹配验证，判断是否符合要求。
	 
节点验证：按照配置的规则对返回报文中每个节点进行正确性验证。
	 

备注：节点验证的配置规则详解

复杂节点路径：例如ROOT.USER.NAME表示root根节点下的user节点下的name节点，如果不选择此项，则类似ROOT.USER.NAME此节点名不做嵌套解析。

获取验证值方式：何种方式来获取用于比对返回报文该节点值的数据。目前提供以下三种：①字符串 ②入参节点(从入参中获取) ③数据库(从指定数据库中获取)
	参数比对值：根据获取验证值方式的不同需要填入不同的内容：
①	字符串：填入普通字符串值 ②入参节点(从入参中获取)：填入入参节点名称 ③数据库(从指定数据库中获取)：查询用的SQL语句

下图展示了对返回报文中的id/name/age三个节点进行正确性验证的规则。

	 	
1.2.2.3场景测试
场景测试可用于对创建的场景进行单次测试调试，可以自行选择测试地址和测试数据。
	 	
	
1.3．	测试集管理
测试集管理用于管理由不同测试场景组合成的测试集合。点击添加按钮输入测试集名称即可创建新的测试集。
 
点击对应测试集下的测试场景数量按钮可对该测试集下的测试场景进行进行管理，自行添加或者减少该测试集下的测试场景。
	 
1.4．	测试执行
测试执行用于配置全局的测试设置和进行测试集测试或者全局测试。
1.4.1.测试设置
	可进行配置的选项说明：
①	接口地址选择：默认选择(按优先级选取测试地址：报文中设置的地址>接口中设置的mock地址>接口中设置的real地址)、优先使用接口中定义的mock地址、优先使用接口中定义的real地址
②	连接超时：与请求地址建立连接的最大超时时间，单位ms
③	读取超时：从已建立的连接中读取返回报文的最大超时时间，单位ms
④	请求方式：GET、POST
⑤	返回值确认：对返回报文的正确性验证的全局设置，尝试获取返回报文中的ReturnCode节点的值，同此设置的值列表进行匹配验证，多个值用英文逗号分隔
⑥	测试前检查数据：测试之前是否需要自动检查该场景是否拥有足够的数据进行本次测试。
⑦	后台执行：后台执行的结果会通过站内信通知用户。

	 
1.4.2测试执行
	测试集测试：选择当前用户创建的指定测试集来进行测试
	全局测试：测试当前所有的测试场景，该操作需要指定权限。
	 
1.5．	测试报告
展示详细的测试集测试或者全量测试的测试报告。

 

通过点击测试成功数、测试失败数、测试异常数连接按钮打开详情页查看指定的测试场景在本次测试中的入参、出参等情况。

 
	
在测试报告列表页的操作选项栏，可以点击打开详细测试报告总览。


1.6．	接口Mock
通过此功能可以在本系统中创建一个对外提供测试的模拟接口。
	 
1.6.1．创建mock接口
打开测试工具模块，点击接口Mock，点击页面“+增加Mock接口”按钮，打开增加页面。按照提示输入对应的内容，点击保存即可。
 

备注：“是否需要验证入参合法性”选项设置了该接口在接收他方调用的时候，是否验证参数的合法性，主要验证传入的参数是否都存在于设定的接口入参中。


1.6.2．调用mock接口
	打开mock接口列表页面，复制需要调用接口的接口地址。
 
	由于该mock接口设置了验证入参：
 
更改选项为不验证并重新调用：
 
2．	Web功能自动化
Web功能自动化集成了Selenium-java测试框架，并将相关常用方法进行进一步的封装，用户在Web页面通过页面提示创建测试用例、测试对象等来间接的编写测试脚本，并通过本地测试客户端来进行本地调试。
	 
2.1．测试流程
通过创建一个测试百度搜索功能的测试用例来展示如何通过本工具来执行Web自动化的基本流程。
2.1.1．创建测试用例
打开用例管理模块，点击“+增加新的测试用例”打开增加页面。输入内容提交即可创建一个新的测试用例。
	 
	 

2.1.2．创建测试对象
在进一步完善测试用例之前我们还需要创建对应用例下所用到的测试对象(测试对象即各种页面上的html元素或者类似iframe层、对话框等)。
 
打开测试对象模块，按照页面提示先创建对应节点：
 

可创建的节点类型包括：website-网站(比如百度)、module-模块(比如用户管理)、feature-功能(比如增加用户)、page-页面。
 
创建好节点之后，点击对应的页面->增加新的测试对象打开测试对象增加页面。
 
相关属性说明：
1、对象类型：即为html页面上对应的元素名，比如button指页面上的按钮、text_field指页面上输入框、link指页面上的网址链接等。如果创建的测试对象不用和html上的元素对应起来，请选择“-”。
 
2、对象获取方式：对应的html元素如何获取到。提供的方法有：根据id获取(推荐)、根据name查找、根据元素标签查找等。其中LinkText只能查找link元素、XPath可以查找所有的元素，需注意。
	 
3、查找顺序：如果通过指定的查找方法查找出的元素不止一个，那么就可以通过此值来指定需要的元素在查找列表的顺序。默认为0。
4、关键字：此值在不同的对象类型和不同的对象获取方式下所表示的含义不同：比如对象类型为url,获取方式为“-”，则该值就为url的值；比如对象类型为text_field，获取方式为XPath，则该值为该元素对象在该页面上的XPath路径。

打开Chrome浏览器的开发者工具台，查找本次测试需要用到的元素(具体使用方法可以参考Selenium使用教程)，分别创建以下几个测试对象：

	 
2.1.3．创建测试步骤
	点击测试用例列表的指定用例下测试步骤个数打开测试步骤页面，点击“+增加新的测试步骤”，选择“创建新的测试步骤”即可打开创建页面。
	 
	根据用例设计，我们可以创建以下几个测试步骤：打开百度首页->输入搜索条件->点击搜索按钮->验证是否搜索正确。
 

相关属性说明：
1、	执行方式：
执行-普通执行，执行完此步骤将会自动执行下一个步骤或者用例执行成功；
验证-将本次操作得到的返回值和预先设置值进行比对,比对成功则该测试用例执行成功或者继续执行下一个步骤,比对不成功或者没有比对值此步骤将会被标记成Fail。此步骤需要一个比对参数(必要参数),该参数支持自定义值、之前步骤的接收参数值和指定数据库查询值；
取值- 此步骤获取的值可供后续步骤使用。需要一个接收参数(必要参数),输入的参数值将会作为该次步骤获取的值的key。
2、	调用方法：本步骤需要执行的调用方法，请根据对象类型和页面提示来选择。
 
3、测试对象：调用方法可能需要用到的测试对象。某些调用方法可能不需要此值，比如getTitle（获取打开的窗口名）、toDefaultFrame（返回到主Frame）、dismissDialog（关闭当前对话框）等。
4、调用方法参数：调用方法可能需要用到的参数，比如sendKeys（发送或者填写字符串内容到对象元素）需要输入指定字符串、toFrame（切换到指定的Frame层）需要对应Frame层的Id或者Name、getAttribute（获取对象元素的属性值）需要属性名称等，请根据调用方法的说明来填写该值。
	5、步骤方法参数：不同的步骤执行方法可能需要一些参数：
		执行步骤：不需要；
验证步骤：不同的预期验证数据获取方式，步骤方法参数需要填入的内容也不相同：字符串（直接输入需要同结果进行验证的字符串值）、取值参数（之前通过取值步骤取到的参数名称）、数据库（查找验证数据用到的SQL语句）；
取值步骤：需要输入一个参数名来接收本次步骤的取值内容，格式为“<接收参数名>”。
	6、是否截图：执行完该步骤是否需要进行截图操作。
2.1.4．执行测试
	点击指定测试用例的执行按钮，提示需要到测试客户端进行执行操作。
	 
	
	打开本地测试客户端(方法可参见2.5．本地测试客户端)，选择指定的提交待测试的任务，点击下方执行测试按钮，客户端会根据测试用例中设置的浏览器类型来打开本地的浏览器开始进行自动化测试，测试完成关闭浏览器并提示。
	 
2.1.5．查看测试报告
打开测试报告管理页面，选择本次测试的测试结果。
 
点击执行步骤将会打开详细的测试报告，包括每个步骤的执行结果、截图、执行时间、备注等。
 

	点击操作栏中的报告生成按钮将会打开本次测试报告总览。
 
2.2．公共步骤库
	公共步骤中保存着一些常用的测试步骤，比如登录、固定的验证等。
	 

2.2.1．添加公共步骤
	打开某个测试用例的测试步骤列表，点击上方工具栏的“推荐我的测试步骤”按钮打开公共测试步骤创建页面。
	 

	选择需要加入的指定步骤(至少需要一个步骤)，填写相关说明设置，点击“推荐审核”即可。
	 

	用户可以在步骤列表页面查看自己推荐的公共步骤审核情况。
	 
	
2.2.2．审核查看公共步骤库
	审核查看公共步骤库需要管理员权限，打开审核页面可以查看用户推荐的公共步骤，点击测试步骤可以查看包含的测试步骤并且可以进行修改(此修改不会影响用户自己测试用例中测试步骤)。 

	 

	点击当前状态可进行审核，审核通过可以在公共步骤库列表查看。
	 

2.2.3．使用公共步骤
	如需要使用公共步骤，在创建测试步骤的时候选择“从公共步骤库中选择”即可。选择的公共步骤会被复制并加入到当前测试用例中的测试步骤，通过“测试步骤排序”功能可对步骤顺序进行调整。
	 
2.3．测试用例集
	测试用例集包含多个测试用例，由管理员创建和维护，用户添加测试用例，管理员进行审核。
测试用例集执行在服务器端。所以必须保证服务端为WindowServer服务器并且配置好各种版本的浏览器和驱动程序。
2.3.1．创建测试用例集
	打开测试用例集模块，点击“+增加新的测试用例集”，输入创建的测试用例集名称即可。
	 
	
	点击测试用例集编辑按钮，打开测试用例集详情。点击“编辑测试集详情”可对该用例集进行详细修改。
	 

2.3.2．添加测试用例
	用户通过点击测试用例操作栏中的“添加到测试集”按钮图标打开可添加到的测试用例集列表。
	 
	点击添加按钮添加该测试用例到指定的测试用例。
	 
	用户可以在用例列表页面查看审核情况。
	 

	管理员用户可在测试用例详情页面处理添加请求。
	 
2.3.3．执行测试用例集
	只有管理员才能执行测试用例集，点击状态为“可用”的测试集的执行按钮，即可在服务端开始执行自动化测试。测试完成之后系统发送站内邮件通知管理员查看测试报告。
	 


2.4．测试配置
	 
	说明：
等待元素出现最大时间：为获取页面上指定元素而等待其出现的最大超时时间，默认为5000ms；
等待获取结果时间：在取值或者验证的测试步骤中，为获取指定结果而等待的最大超时时间，默认为3000ms；
浏览器启动路径：用户个人电脑上各类型浏览器的启动路径(.exe文件绝对路径)，不填则为默认路径；
浏览器大小：执行测试时，浏览器的大小，默认为最大化，可选最小化或者默认大小；
出错执行：执行测试时出现错误时的处理方法，默认为出现错误将会继续进行下一个测试步骤的执行，可选为出现错误即停止该测试用例的执行。
2.5．本地测试客户端
	本地客户端方便编写人员对测试用例的调试。
2.5.1．安装
	使用本地测试客户端要求个人电脑上必须安装JDK1.7+环境，通过工具下载页面下载对应的文件。
	 
2.5.2．使用
	双击WebTest.jar或者在当前文件夹打开命令执行“java –jar WebTest.jar”即可开始使用本地测试客户端。
	 
	输入用户密码登录成功，可以看到由web端发送的测试请求，根据下方工具栏执行不同操作。执行成功之后，返回web端查看测试报告。
	 
2.6．Watir脚本管理
	此模块方便脚本手工编写人员对rubyWatir测试脚本的统一管理执行。
	 
2.6.1．上传脚本
	点击“上传脚本按钮”，打开上传界面。
	 

	按要求填写各内容。测试脚本分为公共脚本和场景脚本，前者只需上传.rb文件，后者则需要上传同名的.rb和.feature文件。上传文件完成之后提交即可。
	 
	 

	通过点击上方“公共脚本”或者“场景脚本”按钮可展示不同类型的脚本。
2.6.2．执行脚本
	选择场景脚本列表中需要执行的场景脚本(默认选择所有的公共脚本)，点击工具栏中“执行脚本”即可在服务端执行测试脚本，测试完成之后，前往“Ruby测试报告”模块查看本次测试报告。

备注：在服务端执行ruby脚本需要提前配置好服务端的Ruby+Watir+Cumumber的测试环境。


