//
//  FEUploadImageVC.m
//  SmartHome
//
//  Created by Seven on 15-4-8.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEUploadImageVC.h"
#import "FEUploadRequest.h"
#import "FEWebServiceManager.h"
#import "Define.h"

@interface FEUploadImageVC ()<UIActionSheetDelegate,UIImagePickerControllerDelegate,UINavigationControllerDelegate>
@property (strong, nonatomic) IBOutlet UIView *imageContent;
@property (strong, nonatomic) IBOutlet UIImageView *imageView;
@property (strong, nonatomic) FEWebServiceManager *webManager;

@end

@implementation FEUploadImageVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}



- (IBAction)pickImage:(id)sender {
    
    UIActionSheet *action = [[UIActionSheet alloc] initWithTitle:@"选择方式" delegate:self cancelButtonTitle:@"取消" destructiveButtonTitle:nil otherButtonTitles:@"相册选取",@"拍照", nil ];
    [action showInView:self.view];
    
}


- (void)actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex{
    if (buttonIndex == 0) {
        [self localPhoto];
    }else if (buttonIndex == 1){
        [self takePhoto];
    }
}

-(void)localPhoto{
    UIImagePickerController *picker = [[UIImagePickerController alloc] init];
    
    picker.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
    picker.delegate = self;
    //设置选择后的图片可被编辑
    picker.allowsEditing = NO;
    [self presentViewController:picker animated:YES completion:nil];
    
}

-(void)takePhoto{
    UIImagePickerControllerSourceType sourceType = UIImagePickerControllerSourceTypeCamera;
    if ([UIImagePickerController isSourceTypeAvailable: UIImagePickerControllerSourceTypeCamera])
    {
        UIImagePickerController *picker = [[UIImagePickerController alloc] init];
        picker.delegate = self;
        //设置拍照后的图片可被编辑
        picker.allowsEditing = NO;
        picker.sourceType = sourceType;
        [self presentViewController:picker animated:YES completion:nil];
    }else
    {
        NSLog(@"模拟其中无法打开照相机,请在真机中使用");
    }
}

- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info{
    NSString *type = [info objectForKey:UIImagePickerControllerMediaType];
    
    //当选择的类型是图片
    if ([type isEqualToString:@"public.image"])
    {
        //先把图片转成NSData
        UIImage* image = [info objectForKey:@"UIImagePickerControllerOriginalImage"];
//        NSData *data;
//        if (UIImagePNGRepresentation(image) == nil)
//        {
//            data = UIImageJPEGRepresentation(image, 0.2);
//        }
//        else
//        {
//            data = UIImagePNGRepresentation(image);
//        }
        self.imageView.image = image;
//        //图片保存的路径
//        //这里将图片放在沙盒的documents文件夹中
//        NSString * DocumentsPath = [NSHomeDirectory() stringByAppendingPathComponent:@"Documents"];
//        
//        //文件管理器
//        NSFileManager *fileManager = [NSFileManager defaultManager];
//        
//        //把刚刚图片转换的data对象拷贝至沙盒中 并保存为image.png
//        [fileManager createDirectoryAtPath:DocumentsPath withIntermediateDirectories:YES attributes:nil error:nil];
//        [fileManager createFileAtPath:[DocumentsPath stringByAppendingString:@"/image.png"] contents:data attributes:nil];
//        
//        //得到选择后沙盒中图片的完整路径
//        filePath = [[NSString alloc]initWithFormat:@"%@%@",DocumentsPath,  @"/image.png"];
//        
//        //关闭相册界面
//        [picker dismissModalViewControllerAnimated:YES];
//        
//        //创建一个选择后图片的小图标放在下方
//        //类似微薄选择图后的效果
//        UIImageView *smallimage = [[[UIImageView alloc] initWithFrame:
//                                    CGRectMake(50, 120, 40, 40)] autorelease];
//        
//        smallimage.image = image;
//        //加在视图中
//        [self.view addSubview:smallimage];
        
    }
    [picker dismissViewControllerAnimated:YES completion:nil];
    
}

- (IBAction)upload:(id)sender {
    if (self.imageView.image != nil) {
        __weak typeof(self) weakself = self;
        FEUploadRequest *rdata = [[FEUploadRequest alloc] init];
        self.webManager = [[FEWebServiceManager alloc] initWithBaseURL:[NSURL URLWithString:__SERVICE_BASE_URL]];
        [self.webManager requstData:rdata appendDAta:^(id<AFMultipartFormData> formDate) {
            [formDate appendPartWithFileData:UIImageJPEGRepresentation(weakself.imageView.image, 0.2) name:@"upload" fileName:@"test.jpg" mimeType:@"image/pjpeg"];
//            [formDate appendPartWithFormData:UIImageJPEGRepresentation(weakself.imageView.image, 0.2) name:@"upload"];
        } responseclass:[FEBaseResponse class] response:^(NSError *error, id response) {
            FEBaseResponse *rsp = response;
            if (!error && rsp.result.errorCode.integerValue == 0) {
                NSLog(@"upload success");
            }
        }];
    }
    

}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
