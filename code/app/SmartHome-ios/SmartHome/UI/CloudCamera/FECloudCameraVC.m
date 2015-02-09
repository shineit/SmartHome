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

@interface FECloudCameraVC ()<YSPlayerControllerDelegate,UICollectionViewDataSource,UICollectionViewDelegate,UICollectionViewDelegateFlowLayout,ZBarReaderDelegate,UITableViewDelegate,UITableViewDataSource>

//@property (nonatomic, strong) UICollectionView *collectionView;
@property (nonatomic, strong) UITableView *tableView;
@property (nonatomic, strong) NSMutableArray *cameras;
@property (nonatomic, strong) YSMobilePages *page;
@property (nonatomic, strong) YSPlayerController *ysPlayer;
@property (nonatomic, strong) NSString *token;
@property (strong, nonatomic) ZBarReaderViewController *zbarReaderVC;

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
    
//    [self loadRightCustomButtonItemWithTitle:FEString(@"添加") image:nil];
    
//    FETableView *table = [[FETableView alloc] initWithFrame:self.view.bounds style:UITableViewStylePlain];
//    table.dataSource = self;
//    table.delegate = self;
//    table.autoresizingMask = UIViewAutoresizingFlexibleHeight;
//    [self.view addSubview:table];
//    self.tableView = table;
    
//    UICollectionViewFlowLayout *layout= [[UICollectionViewFlowLayout alloc]init];
//    UICollectionView *cview = [[UICollectionView alloc] initWithFrame:self.view.bounds collectionViewLayout:layout];
//    cview.alwaysBounceVertical = YES;
//    cview.delegate = self;
//    cview.dataSource = self;
//    cview.autoresizingMask = UIViewAutoresizingFlexibleHeight;
//    [cview registerClass:[FECollectionViewCameraCell class] forCellWithReuseIdentifier:@"cell"];
////    cview.backgroundColor = [UIColor lightGrayColor];
//    cview.backgroundColor = [UIColor clearColor];
//    self.collectionView = cview;
//    [self.view addSubview:cview];
    
}

-(void)rightbarpressed:(UIButton *)button{
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
    ZBarSymbol *symbol = nil;
    for(symbol in results)
        // EXAMPLE: just grab the first barcode
        break;
    NSData *data = [symbol.data dataUsingEncoding:NSUTF8StringEncoding];
    if (data) {
//        id json = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:nil];
    }
}

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
