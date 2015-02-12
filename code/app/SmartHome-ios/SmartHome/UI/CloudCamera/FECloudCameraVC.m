//
//  FECloudCameraVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECloudCameraVC.h"
#import "FECollectionViewCameraCell.h"
#import "YSMobilePages.h"
#import "YSPlayerController.h"
#import "YSHTTPClient.h"
#import <SDWebImage/UIImageView+WebCache.h>
#import "YSCamera.h"
#import "FEWebServiceManager.h"
#import "FECoreDataHandler.h"
#import "CDUser.h"
#import "AppDelegate.h"
#import "FECameraVerfyPhoneVC.h"
#import <ZBarSDK/ZBarReaderViewController.h>
#import "GAAlertObj.h"
#import "FECameraItemCell.h"
#import "FECameraPlayerVC.h"
#import "FECameraCode.h"
#import "FECameraWifiConfigVC.h"

@interface FECloudCameraVC ()<YSPlayerControllerDelegate,UICollectionViewDataSource,UICollectionViewDelegate,UICollectionViewDelegateFlowLayout,ZBarReaderDelegate,UITableViewDelegate,UITableViewDataSource>

//@property (nonatomic, strong) UICollectionView *collectionView;
@property (nonatomic, strong) UITableView *tableView;
@property (nonatomic, strong) NSMutableArray *cameras;
@property (nonatomic, strong) YSMobilePages *page;
@property (nonatomic, strong) YSPlayerController *ysPlayer;
@property (nonatomic, strong) NSString *token;
@property (strong, nonatomic) ZBarReaderViewController *zbarReaderVC;
@property (strong, nonatomic) FECameraCode *ccode;

@end

@implementation FECloudCameraVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"CLOUD_CAMERA");
        
        if (SYSTEM_VERSION_UP7) {
            UITabBarItem *tabitem = [[UITabBarItem alloc] initWithTitle:FEString(@"CLOUD_CAMERA") image:[UIImage imageNamed:@"tabbar_camera"] selectedImage:nil];
            self.tabBarItem = tabitem;
        }else{
            [self.tabBarItem setFinishedSelectedImage:[UIImage imageNamed:@"tabbar_camera_select"] withFinishedUnselectedImage:[UIImage imageNamed:@"tabbar_camera"]];
        }
        
//        self.cameras = [[NSMutableArray alloc] initWithObjects:@"camera1",@"camera2",@"camera3",@"camera4", nil];
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
    
    [self loadCamera];
}

-(void)initUI{
    
    self.tableView.tableFooterView = [UIView new];
}

- (IBAction)addDevice:(id)sender {
    ZBarReaderViewController *reader = [ZBarReaderViewController new];
    self.zbarReaderVC = reader;
    UIView *view = [[UIView alloc] initWithFrame:CGRectMake(0, reader.view.bounds.size.height - 54, reader.view.bounds.size.width, 54)];
    view.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight | UIViewAutoresizingFlexibleTopMargin;
    view.backgroundColor = [UIColor blackColor];
    
    UIToolbar *toolbar = [UIToolbar new];
    toolbar.frame = CGRectMake(0, 0, view.bounds.size.width, view.bounds.size.height);
    toolbar.barStyle = UIBarStyleBlackOpaque;
    toolbar.autoresizingMask =
    UIViewAutoresizingFlexibleWidth |
    UIViewAutoresizingFlexibleHeight;
    
    toolbar.items = [NSArray arrayWithObjects:[[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemCancel target:self action:@selector(cancel)],[[UIBarButtonItem alloc] initWithBarButtonSystemItem: UIBarButtonSystemItemFlexibleSpace target: nil action: nil],nil];
    [view addSubview: toolbar];
    
    
    reader.cameraOverlayView = view;
    reader.showsZBarControls = NO;
    reader.readerDelegate = self;
    reader.supportedOrientationsMask = ZBarOrientationMaskAll;
    
    ZBarImageScanner *scanner = reader.scanner;
    
    // TODO: (optional) additional reader configuration here
    
    // EXAMPLE: disable rarely used I2/5 to improve performance
    [scanner setSymbology: ZBAR_I25
                   config: ZBAR_CFG_ENABLE
                       to: 0];
    
    // present and release the controller
    [self presentViewController:reader animated:YES completion:nil];
}

-(void)cancel{
    [self.zbarReaderVC dismissViewControllerAnimated:YES completion:nil];
}

#pragma mark - UIImagePickerViewDelegate
- (void) imagePickerController: (UIImagePickerController*) reader
 didFinishPickingMediaWithInfo: (NSDictionary*) info
{
    // ADD: get the decode results
    id<NSFastEnumeration> results =
    [info objectForKey: ZBarReaderControllerResults];
//    ZBarSymbol *symbol = nil;
    NSString * strQRcode = nil;
    for (ZBarSymbol *sym in results)
    {
        strQRcode = sym.data;
        break;
    }
    
    if ([strQRcode length] == 0)
    {
        return;
    }
    
    FECameraCode *code = [[FECameraCode alloc] initWithString:strQRcode];
    if (code.m_strSN.length == 9) {
//        __weak typeof(self) weakself = self;
        self.ccode = code;
        [reader dismissViewControllerAnimated:YES completion:nil];
        [self performSegueWithIdentifier:@"configWifiSegue" sender:nil];
        
    }
}


/**
 *  处理获取到的二维码
 *
 *  @param strQRCode 二维码信息
 */
//- (FECameraCode *)dealScanQR:(NSString *)strQRCode
//{
//    NSLog(@"read QRcode: %@", strQRCode);
//    
//    //设备二维码名片
//    FECameraCode *qcode = [[FECameraCode alloc] initWithString:strQRCode];
////    self.m_strSN = [self snFromQRcode:strQRCode];
//    
//    NSLog(@"read SN: %@, verify is %@, model is %@", qcode.m_strSN, qcode.strVerifyCode, qcode.strModel);
//    
//    BOOL avaliable = [qcode.m_strSN length] == 9;
    
//    dispatch_async(dispatch_get_main_queue(), ^
//                   {
//                       if (avaliable)
//                       {
////                           [self pushToResult];
//                       }
//                       else
//                       {
////                           [CAttention showCustomAutoHiddenAttention:NSLocalizedString(@"无法识别的二维码", nil)
////                                                              toView:self.view
////                                                           toYOffset:-30
////                                                          toFontSize:nil
////                                                   toIsDimBackground:NO];
//                           
//                       }
//                   });
//    
//    return qcode;
//    
//}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
//    static NSString *identifier = @"cell";
    YSCamera *camera = self.cameras[indexPath.row];
    FECameraItemCell *cell = [tableView dequeueReusableCellWithIdentifier:@"cameraCell" forIndexPath:indexPath];
    [cell configWithCamera:camera];
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.cameras.count;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 90;
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if ([sender isKindOfClass:[FECameraItemCell class]]) {
        FECameraItemCell *cell = sender;
        FECameraPlayerVC *vc = segue.destinationViewController;
        vc.accessToken = self.accessToken;
        vc.camera = cell.camera;
    }else if ([segue.identifier isEqualToString:@"configWifiSegue"]){
        FECameraWifiConfigVC *vc = segue.destinationViewController;
        vc.ccode = self.ccode;
        vc.accessToken = self.accessToken;
    }
}


#pragma mark - UICollectionViewDataSource

// The cell that is returned must be retrieved from a call to -dequeueReusableCellWithReuseIdentifier:forIndexPath:
- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    static NSString * identifier = @"cell";
    FECollectionViewCameraCell * cell = [collectionView dequeueReusableCellWithReuseIdentifier:identifier forIndexPath:indexPath];
    YSCamera *item = self.cameras[indexPath.row];
    cell.backgroundColor = [UIColor whiteColor];
    cell.textLabel.text = item.cameraName;
    [cell.imageView sd_setImageWithURL:[NSURL URLWithString:item.picUrl]];
    return cell;
}

-(NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView
{
    return 1;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    return self.cameras.count;
}

#pragma mark - UICollectionViewDelegateFlowLayout

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath
{
    return CGSizeMake(140, 140);
}

-(UIEdgeInsets)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout insetForSectionAtIndex:(NSInteger)section
{
    return UIEdgeInsetsMake(10, 10, 10, 10);
}

-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{
//    RealPlayViewController *realPlayController = [[RealPlayViewController alloc] init];
//    realPlayController.cameraInfo = cell.cameraInfo;
//    [self.navigationController pushViewController:realPlayController animated:YES];
//    [realPlayController release];
    YSCamera *camera = self.cameras[indexPath.row];
    [self.ysPlayer startRealPlayWithCamera:camera.cameraId accessToken:self.token inView:self.view];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (void)playerOperationMessage:(YSPlayerMessageType)msgType withValue:(id)value{
    
}

-(void)loadCamera{
    __weak typeof(self) weakself = self;
    [[YSHTTPClient sharedInstance] setClientAccessToken:self.accessToken];
    [[YSHTTPClient sharedInstance] requestSearchCameraListPageFrom:0 pageSize:10 complition:^(id responseObject, NSError *error) {
        if (responseObject) {
            NSDictionary *dictionary = (NSDictionary *)responseObject;
            NSNumber *resultCode = [dictionary objectForKey:@"resultCode"];
            if (resultCode.intValue == 200) {
                NSMutableArray *carray = [NSMutableArray new];
                for (NSDictionary *item in responseObject[@"cameraList"]) {
                    [carray addObject:[[YSCamera alloc] initWithDictionary:item]];
                }
                weakself.cameras = carray;
                [weakself.tableView reloadData];
//                [weakself.collectionView reloadData];
            }
            
        }
    }];

    
}
- (IBAction)deleteCamera:(id)sender {
    
    id sview;
    if (SYSTEM_VERSION_LESS_THAN(@"8.0")) {
        sview = [[[sender superview] superview] superview];
    }else{
        sview = [[sender superview] superview];
    }
    
    if ([sview isKindOfClass:[FECameraItemCell class]]) {
        FECameraItemCell *cell = sview;
        YSCamera *camera = cell.camera;
        __weak typeof(self) weakself = self;
        [self displayHUD:@"删除中..."];
        [[YSHTTPClient sharedInstance] requestDeleteCameraWithCameraId:camera.deviceId complition:^(id responseObject, NSError *error) {
            NSNumber *resultCode = [responseObject objectForKey:@"resultCode"];
            
            if (!error && resultCode.integerValue == 200) {
                [weakself.cameras removeObject:camera];
                [weakself.tableView reloadData];
            }
            [weakself hideHUD:YES];
        }];
    }
    
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
